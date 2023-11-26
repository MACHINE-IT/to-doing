package com.example.service.impl;

import com.example.model.Category;
import com.example.model.CategoryTable;
import com.example.model.Task;
import com.example.model.User;
import com.example.repository.CategoryRepository;
import com.example.repository.TaskRepository;
import com.example.repository.UserRepository;
import com.example.request.TaskRequest;
import com.example.request.TaskShareRequest;
import com.example.request.TaskUpdateRequest;
import com.example.response.NotificationDTO;
import com.example.response.TaskResponse;
import com.example.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;


    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;

        this.categoryRepository = categoryRepository;
    }


    @Override
    public TaskResponse createTask(User owner, TaskRequest taskRequest) throws Exception {
        taskRequest.setCategory(Category.valueOf(taskRequest.getCategory().toString()));
        LocalDateTime dueDate = taskRequest.getDueDate();
        if(dueDate.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("due date cannot set in past");
        } else {
            Task task = modelMapper.map(taskRequest, Task.class);
            task.setOwner(owner);
            task.setId(null);
            task.setCreationDate(LocalDateTime.now());
            Task savedTask = taskRepository.save(task);
            return modelMapper.map(savedTask, TaskResponse.class);
        }
    }


    @Override
    @Cacheable(value = "taskResponses", key = "#taskId")
    public TaskResponse getTaskById(long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NoSuchElementException("no task found with " + taskId));
        return modelMapper.map(task, TaskResponse.class);
    }

    @Override
    @CacheEvict(value = "taskResponses", key = "#taskUpdateRequest.getId()") // Assuming TaskResponse has a userId field
    public TaskResponse updateTask(TaskUpdateRequest taskUpdateRequest) {
        if(taskRepository.findById(taskUpdateRequest.getId()).isEmpty()) {
            throw new NoSuchElementException("No task found with given task id");
        }

        Task updatedTask = modelMapper.map(taskUpdateRequest, Task.class);
        Task savedTask = taskRepository.save(updatedTask);
        return modelMapper.map(savedTask, TaskResponse.class);
    }

    @Override
    @CacheEvict(value = "tasks", key = "{#userId, #pageable.pageNumber, #pageable.pageSize}")
    public void deleteTask(long taskId) {
        if(!taskRepository.existsById(taskId)) {
            throw new NoSuchElementException("task with given id does not exist");
        }
         taskRepository.deleteById(taskId);
    }

    @Override
    public void addCategoryToTask(long userId, Long taskId, Long categoryId) throws IllegalAccessException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found with ID: " + taskId));

        CategoryTable category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NoSuchElementException("Category not found with ID: " + categoryId));

        boolean isUserOwner = checkUserOwnershipForTasksAndCategory(userId, taskId, categoryId);
        if(!isUserOwner) {
            throw new IllegalAccessException("User is not authorized to add this category to the task");
        }
        task.setCategory(category); // Assuming Task has a setCategory method
        taskRepository.save(task);
    }

    @Override
    public void removeCategoryFromTask(long userId, Long taskId, Long categoryId) throws IllegalAccessException {
        // Check user ownership
        boolean isUserOwner = checkUserOwnershipForTasksAndCategory(userId, taskId, categoryId);
        if (!isUserOwner) {
            throw new IllegalAccessException("User is not authorized to remove this category from the task");
        }

        // Fetch the task
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found with ID: " + taskId));

        // Check if task is associated with the given category
        if (task.getCategory() != null && task.getCategory().getId() == categoryId) {
            // Remove the association
            task.setCategory(null);
            taskRepository.save(task);
        } else {
            throw new NoSuchElementException("Task is not associated with the specified category");
        }
    }

    public boolean checkUserOwnershipForTasksAndCategory(long userId, long taskId, long categoryId) throws IllegalAccessException {
        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found with ID: " + taskId));

        CategoryTable category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NoSuchElementException("Category not found with ID: " + categoryId));

        return task.getOwner().getUserId().equals(userId) && category.getCategoryOwner().getUserId().equals(userId);
    }


}

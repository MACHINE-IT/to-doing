package com.example.service.impl;

import com.example.model.Category;
import com.example.model.Task;
import com.example.model.User;
import com.example.repository.TaskRepository;
import com.example.request.TaskRequest;
import com.example.request.TaskUpdateRequest;
import com.example.response.TaskResponse;
import com.example.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;


@Service
@Transactional
@Slf4j
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;

    }


    @Override
    public TaskResponse createTask(User owner, TaskRequest taskRequest) {
        taskRequest.setCategory(Category.valueOf(taskRequest.getCategory().toString()));
        Task task = modelMapper.map(taskRequest, Task.class);
        task.setOwnerId(owner);
        task.setId(null);
        task.setCreatedAt(LocalDateTime.now());
        Task savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask, TaskResponse.class);
    }


    @Override
    public TaskResponse getTaskById(long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NoSuchElementException("no task found with " + taskId));
        return modelMapper.map(task, TaskResponse.class);
    }

    @Override
    public TaskResponse updateTask(TaskUpdateRequest taskUpdateRequest) {
        if(taskRepository.findById(taskUpdateRequest.getId()).isEmpty()) {
            throw new NoSuchElementException("No task found with given task id");
        }

        Task updatedTask = modelMapper.map(taskUpdateRequest, Task.class);
        Task savedTask = taskRepository.save(updatedTask);
        return modelMapper.map(savedTask, TaskResponse.class);
    }

    @Override
    public void deleteTask(long taskId) {
        if(!taskRepository.existsById(taskId)) {
            throw new NoSuchElementException("task with given id does not exist");
        }
         taskRepository.deleteById(taskId);
    }
}

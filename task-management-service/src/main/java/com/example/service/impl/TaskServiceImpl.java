package com.example.service.impl;

import com.example.model.Category;
import com.example.model.Task;
import com.example.repository.TaskRepository;
import com.example.request.TaskRequest;
import com.example.request.TaskUpdateRequest;
import com.example.response.TaskResponse;
import com.example.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void setTaskPriority() {

    }

    @Override
    public void updateCategory() {

    }

    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        taskRequest.setCategory(Category.valueOf(taskRequest.getCategory().toString()));
        Task task = modelMapper.map(taskRequest, Task.class);
        Task savedTask = taskRepository.save(task);
        TaskResponse taskResponse = modelMapper.map(savedTask, TaskResponse.class);
        return taskResponse;
    }


    @Override
    public TaskResponse getTaskById() {
        return null;
    }

    @Override
    public TaskResponse updateTask(TaskUpdateRequest taskUpdateRequest) {
        if(taskRepository.findById(taskUpdateRequest.getId()).isEmpty()) {
            throw new NoSuchElementException("No task found with given task id");
        }
//        Task task = taskRepository.findById(taskUpdateRequest.getTaskId())
//                .orElseThrow(() -> new NoSuchElementException("No task found with give task id"));
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

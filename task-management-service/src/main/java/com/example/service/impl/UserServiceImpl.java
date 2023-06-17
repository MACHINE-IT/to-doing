package com.example.service.impl;

import com.example.model.Task;
import com.example.repository.TaskRepository;
import com.example.response.TaskResponse;
import com.example.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final TaskRepository taskRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TaskResponse> getAllTasks(long userId) {
        List<Task> listOfTasks = taskRepository.findByUserId(userId);
        List<TaskResponse> listOfTaskResponses = listOfTasks.stream()
                .map((element) -> modelMapper.map(element, TaskResponse.class))
                .collect(Collectors.toList());
        return listOfTaskResponses;
    }
}

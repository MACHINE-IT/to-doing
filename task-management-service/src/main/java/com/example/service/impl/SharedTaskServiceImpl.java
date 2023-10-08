package com.example.service.impl;

import com.example.model.Task;
import com.example.model.User;
import com.example.repository.TaskRepository;
import com.example.request.TaskShareRequest;
import com.example.service.SharedTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class SharedTaskServiceImpl implements SharedTaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public SharedTaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    @Transactional
    public void share(TaskShareRequest taskShareRequest) {
        Task task = taskShareRequest.getTask();
        Set<User> newTaskMembers = taskShareRequest.getUserSet();
        task.getTaskMembers().addAll(newTaskMembers);
        taskRepository.save(task);
    }

    @Override
    public void unShare(TaskShareRequest taskShareRequest) {

    }
}

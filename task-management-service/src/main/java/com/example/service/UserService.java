package com.example.service;

import com.example.response.TaskResponse;

import java.util.List;

public interface UserService {

    public List<TaskResponse> getAllTasks(long userId);

}

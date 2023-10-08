package com.example.service;

import com.example.model.Task;
import com.example.model.User;
import com.example.request.TaskRequest;
import com.example.request.TaskUpdateRequest;
import com.example.response.TaskResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface TaskService {
//    TaskResponse createTask(TaskRequest taskRequest);

    TaskResponse createTask(User owner, TaskRequest taskRequest);

    TaskResponse getTaskById(long taskId);

    TaskResponse updateTask(TaskUpdateRequest taskUpdateRequest);

    void deleteTask(long taskId);

    private List<Task> filterByCategory() {
        return null;
    }

    private List<Task> filterByPriority() {
        return null;
    }
}

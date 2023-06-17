package com.example.service;

import com.example.model.Task;
import com.example.request.TaskRequest;
import com.example.request.TaskUpdateRequest;
import com.example.response.TaskResponse;

import java.util.List;

public interface TaskService {
    TaskResponse createTask(TaskRequest taskRequest);
    TaskResponse getTaskById();
    TaskResponse updateTask(TaskUpdateRequest taskUpdateRequest);
    void deleteTask(long taskId);
    void setTaskPriority();
    void updateCategory();

    private List<Task> filterByCategory() {
        return null;
    }

    private List<Task> filterByPriority() {
        return null;
    }
}

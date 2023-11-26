package com.example.service;

import com.example.model.Task;
import com.example.model.User;
import com.example.request.TaskShareRequest;
import com.example.request.TaskUnShareRequest;

public interface SharedTaskService {

    public void share(TaskShareRequest taskShareRequest);

    void unShare(TaskUnShareRequest taskUnShareRequest);
}

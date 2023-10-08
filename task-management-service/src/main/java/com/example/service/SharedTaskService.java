package com.example.service;

import com.example.request.TaskShareRequest;

public interface SharedTaskService {

    public void share(TaskShareRequest taskShareRequest);

    public void unShare(TaskShareRequest taskShareRequest);
}

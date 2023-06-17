package com.example.config;


import com.example.response.TaskResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public final class RestEndpoints {
    public static final String TASKS = "/api/tasks";
    public static final String USER = "/api/users";
    public static final String AUTH = "/api/auth";
    public static final String CREATE_TASK = "/";
    public static final String DELETE_TASK = "/{taskId}";
    public static final String UPDATE_TASK = "/{taskId}";
    public static final String GET_BY_USERID = "/{userId}";
    public static final String GET_TASK_BY_ID = "/{taskId}";

    public static final String SIGN_IN = "/signin";
    public static final String REGISTER = "/register";
    public static final String SIGN_OUT = "/signout";

}

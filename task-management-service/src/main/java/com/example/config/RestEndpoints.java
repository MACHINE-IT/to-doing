package com.example.config;

public final class RestEndpoints {

    public static final String TASKS = "/api/tasks";
    public static final String USER = "/api/users";
    public static final String AUTH = "/api/auth";
    public static final String CREATE_TASK = "/";
    public static final String DELETE_TASK = "/{taskId}";
    public static final String UPDATE_TASK = "/";
    public static final String GET_BY_USERID = "/{userId}/tasks";
    public static final String SIGN_IN = "/signin";
    public static final String REGISTER = "/register";
    public static final String SIGN_OUT = "/signout";
    public static final String RESET_PASSWORD = "/reset-password/*";


}

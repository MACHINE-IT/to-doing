package com.example.config;

public final class RestEndpoints {

    // Authentication and User Management Endpoints
    public static final String AUTH = "/api/auth";
    public static final String USER = "/api/users";
    public static final String SIGN_IN = "/sign-in";
    public static final String REGISTER = "/register";
    public static final String FORGOT_PASSWORD = "/forgot-password";
    public static final String RESET_PASSWORD = "/reset-password/*";
    public static final String SIGN_OUT = "/sign-out";

    // User Profile Endpoints
    public static final String GET_PROFILE_BY_USER_ID = ""; // Add path here
    public static final String UPDATE_PROFILE_USER_ID = ""; // Add path here

    // Task Management Endpoints
    public static final String TASKS = "/api/tasks";
    public static final String CREATE_TASK = ""; // Assuming POST method is used
    public static final String GET_TASK_FOR_AUTHENTICATED_USER = "/tasks";
    public static final String GET_TASK_BY_TASKID = "/{taskId}";
    public static final String UPDATE_TASK = "/{taskId}";
    public static final String DELETE_TASK = "/{taskId}";

    public static final String SET_REMINDER = "/{taskId}/reminder";
    public static final String GET_REMINDERS = "/{taskId}/reminder";
    public static final String UPDATE_REMINDER = "/{taskId}/reminder";
    public static final String DELETE_REMINDER = "/{taskId}/reminders/{reminderId}";
    public static final String SHARE_THE_TASK = "/{taskId}/share";

    public static final String UNSHARE_THE_TASK = "/{taskId}/unshare";
    public static final String ASSIGN_TASK_TO_USER = "/{taskId}/assign";

    public static final String ADD_CATEGORY_FROM_TASK =  "/{taskId}/categories/{categoryId}";
    public static final String REMOVE_CATEGORY_FROM_TASK = ADD_CATEGORY_FROM_TASK;

    // Category Management Endpoints
    public static final String BASE_CATEGORY_API = "/api/categories";
    public static final String CREATE_CATEGORY = BASE_CATEGORY_API;
    public static final String GET_ALL_CATEGORIES = BASE_CATEGORY_API;
    public static final String UPDATE_CATEGORY =  "/{categoryId}";
    public static final String DELETE_CATEGORY =  "/{categoryId}";

    // Task Sharing Endpoints
  // -> only owner can invite others

    // ... any additional groups or endpoints ...
}

/*
* Reset Password
* 1. User clicks on forgot password
* 2. User enters email
* 3. User receives email with reset password link
* 4. User clicks on reset password link
* 5. User enters new password
* 6. User clicks on submit
* 7. link will be validated
* 8. User receives email with password reset confirmation
*
*
* rest api for marking task as done
* rest api for marking task as undone
*
* rest api for creating group
* rest api for sharing tasks
* rest api for adding members to group
* rest api for removing members from group
* rest api for deleting group
* rest api for updating group
* rest api for getting all groups
* rest api for getting group by id
* rest api for getting all members of group
* rest api for getting all tasks of group
* rest api for getting all tasks of group by member
* rest api for getting all tasks of group by member and status
* rest api for setting permissions for group
* rest api for getting permissions for group
* rest api for getting all tasks of group by member and status and priority
* rest api for getting all tasks of group by member and status and priority and date
* rest api for getting all tasks of group by member and status and priority and date and time
* rest api for getting all tasks of group by member and status and priority and date and time and category
* rest api for creating analytics for all tasks
*
*
*
* */

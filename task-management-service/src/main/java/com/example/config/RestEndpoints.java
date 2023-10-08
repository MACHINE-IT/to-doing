package com.example.config;

public final class RestEndpoints {

    public static final String TASKS = "/api/tasks";
    public static final String USER = "/api/users";
    public static final String AUTH = "/api/auth";
    public static final String CREATE_TASK = "";
    public static final String DELETE_TASK = "/{taskId}";
    public static final String UPDATE_TASK = "/{taskId}";
    public static final String GET_BY_USERID = "/user/tasks";
    public static final String GET_TASK_BY_TASKID = "/{taskId}";
    public static final String SIGN_IN = "/signin";
    public static final String REGISTER = "/register";
    public static final String FORGOT_PASSWORD = "/forgot-password";

    public static final String SIGN_OUT = "/signout";

    public static final String RESET_PASSWORD = "/reset-password/*";

    public static final String GET_PROFILE_BY_USER_ID = "";

    public static final String UPDATE_PROFILE_USER_ID = "";

    public static final String SHARE_THE_TASK = "/tasks/{taskId}"; // -> only owner can invite others

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

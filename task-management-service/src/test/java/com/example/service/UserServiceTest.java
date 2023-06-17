package com.example.service;

import com.example.response.TaskResponse;
import com.example.taskmanagementservice.TaskManagementServiceApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = TaskManagementServiceApplication.class)
public class UserServiceTest {

    private final UserService userService;

    @Autowired
    public UserServiceTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void getAllThetasksByUserIdTest() {
        long userId = 1L;
        List<TaskResponse> taskResponseList = userService.getAllTasks(userId);
        Assertions.assertTrue(taskResponseList.size() > 0);
    }
}

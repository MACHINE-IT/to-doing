package com.example.service;

import com.example.service.impl.AuthServiceImpl;
import com.example.taskmanagementservice.TaskManagementServiceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TaskManagementServiceApplication.class)
public class AuthServiceTest {

    private final AuthService authService;

    @Autowired
    public AuthServiceTest(AuthServiceImpl authService) {
        this.authService = authService;
    }


    @Test
    void generateResetUrlTest() {
        System.out.println(authService.generateResetUrl());
    }

    @Test
    void forgotPasswordTest() {
        String email = "laneyij534@akoption.com";
        authService.forgotPassword(email);
    }
}

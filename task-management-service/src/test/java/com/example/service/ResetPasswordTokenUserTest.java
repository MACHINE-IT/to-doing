package com.example.service;

import com.example.service.impl.ResetPasswordTokenServiceImpl;
import com.example.taskmanagementservice.TaskManagementServiceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TaskManagementServiceApplication.class)
public class ResetPasswordTokenUserTest {

    private final ResetPasswordTokenService resetPasswordTokenService;

    @Autowired
    public ResetPasswordTokenUserTest(ResetPasswordTokenServiceImpl resetPasswordTokenService) {
        this.resetPasswordTokenService = resetPasswordTokenService;
    }


    @Test
    void generatePasswordTokenForUserTest() {
         String token = resetPasswordTokenService.generatePasswordTokenForUser();

        System.out.println("the token is" + token);
    }
}

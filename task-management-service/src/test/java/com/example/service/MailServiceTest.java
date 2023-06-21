package com.example.service;

import com.example.service.impl.MailServiceImpl;
import com.example.taskmanagementservice.TaskManagementServiceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TaskManagementServiceApplication.class)
public class MailServiceTest {

    private final MailService mailService;

    @Autowired
    public MailServiceTest(MailServiceImpl mailService) {
        this.mailService = mailService;
    }

    @Test
    void sendEmailTest() {
        String body = "If you can read this email that means the email service for to doing is working fine!";
        mailService.send("laneyij534@akoption.com","This is from to doing team", body);
    }
}

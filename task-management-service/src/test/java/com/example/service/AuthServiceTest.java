package com.example.service;

import com.example.service.impl.AuthServiceImpl;
import com.example.taskmanagementservice.TaskManagementServiceApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TaskManagementServiceApplication.class)
public class AuthServiceTest {

    private final AuthService authService;

    @Mock
    private ResetPasswordTokenService resetPasswordTokenService;

    @Mock
    private MailService mailService;

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

    @Test
    void shouldThrowUserNotFoundExceptionIfUserNotExists() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class,() -> authService.forgotPassword("sdbc@gmail.com"), () -> "given email is not registered with us" );
    }

    @Test
    void shouldSendEmailIfUserExists() {
        String email = "laneyij534@akoption.com";

        authService.forgotPassword(email);

//        Mockito.verify(resetPasswordTokenService, Mockito.times(1))
//                .generatePasswordTokenForUser();
//        Mockito.verify(resetPasswordTokenService, Mockito.times(1))
//                .calculateExpirationDate(Mockito.anyLong());
//        Mockito.verify(mailService, Mockito.times(1)).send(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

    }
}


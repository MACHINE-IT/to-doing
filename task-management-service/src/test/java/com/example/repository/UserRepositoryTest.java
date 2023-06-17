package com.example.repository;

import com.example.model.User;
import com.example.taskmanagementservice.TaskManagementServiceApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.exceptions.ExceptionIncludingMockitoWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.NoSuchElementException;
import java.util.Optional;

@SpringBootTest
@ContextConfiguration(classes = TaskManagementServiceApplication.class)
public class UserRepositoryTest {

    private final UserRepository userRepository;


    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Test
    void findByEmail() throws Exception {
        String usernameOrEmail = "sandeep@gmail.com";
        User user = userRepository.findUserByEmail(usernameOrEmail)
                .orElseThrow(() -> new Exception("df"));

        Assertions.assertEquals(user.getEmail(), usernameOrEmail);
    }

//    void findByEmailTest()
}

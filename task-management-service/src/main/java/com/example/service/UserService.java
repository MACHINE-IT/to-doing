package com.example.service;

import com.example.model.User;
import com.example.response.TaskResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    public List<TaskResponse> getAllTasks(long userId, Pageable pageable);

    User getUserIdByToken(HttpServletRequest httpServletRequest);
}

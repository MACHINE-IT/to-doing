package com.example.service.impl;

import com.example.model.Task;
import com.example.model.User;
import com.example.repository.TaskRepository;
import com.example.repository.UserRepository;
import com.example.response.TaskResponse;
import com.example.service.UserService;
import com.example.utils.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final JwtService jwtService;

    private final TaskRepository taskRepository;

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(JwtService jwtService, TaskRepository taskRepository, ModelMapper modelMapper,
                           UserRepository userRepository) {
        this.jwtService = jwtService;
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<TaskResponse> getAllTasks(long userId, Pageable pageableRequest) {

        Optional<User> user = userRepository.findById(userId);
        Page<Task> listOfTasks = taskRepository.findByOwnerId(user.get(), pageableRequest);
        return listOfTasks.stream()
                .map((element) -> modelMapper.map(element, TaskResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public User getUserIdByToken(HttpServletRequest httpServletRequest) {
        String jwtToken = (String) httpServletRequest.getAttribute("jwtToken");
        String username = jwtService.extractUsername(jwtToken);
        Optional<User> user = userRepository.findUserByUsername(username);
        if(user.isEmpty()) {
            log.error("user is not found: {} ", user);
            throw new BadCredentialsException("No User found");
        }
        return user.get();
    }
}

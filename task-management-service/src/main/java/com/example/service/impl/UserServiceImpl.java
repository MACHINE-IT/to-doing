package com.example.service.impl;

import com.example.model.Category;
import com.example.model.Task;
import com.example.model.TaskStatus;
import com.example.model.User;
import com.example.repository.TaskRepository;
import com.example.repository.UserRepository;
import com.example.response.TaskMember;
import com.example.response.TaskResponse;
import com.example.service.UserService;
import com.example.utils.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.*;
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
//    @Cacheable(value = "tasks", key = "{#userId, #pageableRequest.pageNumber, #pageableRequest.pageSize}")
    public List<TaskResponse> getAllTasks(long userId, Pageable pageableRequest) {

        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new RuntimeException("No user found");
        }
        Page<Task> listOfTasks = taskRepository.findByOwnerId(userId, pageableRequest);
        List<TaskResponse> taskResponseList = listOfTasks.stream()
                .map((task) -> TaskResponse.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .category(task.getCategory())
                        .description(task.getDescription())
                        .dueDate(task.getDueDate())
                        .taskStatus(task.getTaskStatus())
                        .completionDate(task.getCompletionDate())
                        .reminders(task.getReminders())
//                        .priority(task.getPriority())
                        .build())
                .toList();
        for(Task task: listOfTasks) {
            taskResponseList.forEach(taskResponse -> {
                List<String> usernamesList = task.getSharedWithUsers().stream().map(User::getUsername).toList();
//                taskResponse.setMembers(usernamesList);
            });
        }
        return taskResponseList;
    }

    @Override
//    @Cacheable(value = "filteredTasks", key = "{#userId, #statuses, #categories, #pageableRequest.pageNumber, #pageableRequest.pageSize}")
    public List<TaskResponse> getAllTasksWithFiltersApplied(long userId, List<TaskStatus> statuses, List<Category> categories, Pageable pageableRequest) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new RuntimeException("No user found");
        }

//        Page<Task> listOfTasks = taskRepository.findByOptionalCriteria(user.get(), statuses, categories, pageableRequest);
        Page<Task> byOwnerIdAndTaskStatus = taskRepository.findByOwnerIdAndTaskStatus(userId, statuses, pageableRequest);
        Page<Task> byOwnerIdAndCategory = taskRepository.findByOwnerIdAndCategory(userId, categories, pageableRequest);
        Set<Task> listOfTasks = new HashSet<>();
        listOfTasks.addAll(byOwnerIdAndTaskStatus.getContent());
        listOfTasks.addAll(byOwnerIdAndCategory.getContent());
        List<TaskResponse> taskResponseList = listOfTasks.stream()
                .map((task) -> TaskResponse.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .category(task.getCategory())
                        .description(task.getDescription())
                        .dueDate(task.getDueDate())
                        .taskStatus(task.getTaskStatus())
                        .completionDate(task.getCompletionDate())
                        .reminders(task.getReminders())

//                        .priority(task.getPriority())
                        .build())
                .toList();
        for(Task task: listOfTasks) {
            taskResponseList.forEach(taskResponse -> {
                List<String> usernamesList = task.getSharedWithUsers().stream().map(User::getUsername).toList();
//                taskResponse.setMembers(usernamesList);
            });
        }
        return taskResponseList;
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

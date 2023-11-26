package com.example.Controller;

import com.example.config.RestEndpoints;
import com.example.model.Category;
import com.example.model.TaskStatus;
import com.example.repository.TaskRepository;
import com.example.response.TaskResponse;
import com.example.service.SharedTaskService;
import com.example.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(RestEndpoints.USER)
//@CrossOrigin(allowCredentials = "true", origins = "{http://localhost:3000,http://localhost:8181}")
public class UserController {
    private final UserService userService;
    private final TaskRepository taskRepository;

    private final SharedTaskService sharedTaskService;

    @Autowired
    public UserController(UserService userService,
                          TaskRepository taskRepository, SharedTaskService sharedTaskService) {
        this.userService = userService;
        this.taskRepository = taskRepository;
        this.sharedTaskService = sharedTaskService;
    }

    @GetMapping( RestEndpoints.GET_TASK_FOR_AUTHENTICATED_USER)
    public ResponseEntity<?> getAllTasksForUser(HttpServletRequest request,
                                                @RequestParam(name = "sortBy", defaultValue = "title") String sortBy,
                                                @RequestParam(name = "pageNum", defaultValue = "0") Integer pageNum,
                                                @RequestParam(name = "size", defaultValue = "5") Integer pageSize,
                                                @RequestParam(name = "status", required = false) List<String> taskStatuses,
                                                @RequestParam(name = "category", required = false) List<String> categories
                                                ) {

        List<TaskStatus> statusEnums = taskStatuses != null ?
                taskStatuses.stream().map(String::toUpperCase).map(TaskStatus::valueOf).collect(Collectors.toList())
                        : Collections.emptyList();

        List<Category> categoryList = categories != null ?
                categories.stream().map(String::toUpperCase).map(Category::valueOf).toList() :
                        Collections.emptyList();
        List<TaskResponse> taskResponseList = null;
        try {
            Pageable sortByGivenColumn = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
            long userId = userService.getUserIdByToken(request).getUserId();
            if(statusEnums.isEmpty() && categoryList.isEmpty()) {
                taskResponseList = userService.getAllTasks(userId, sortByGivenColumn);
            } else {
                taskResponseList = userService.getAllTasksWithFiltersApplied(userId, statusEnums, categoryList, sortByGivenColumn);
            }
            return ResponseEntity.ok().body(taskResponseList);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid sortBy value " + sortBy);
        }
    }




}

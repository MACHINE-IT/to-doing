package com.example.Controller;

import com.example.config.RestEndpoints;
import com.example.response.TaskResponse;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping(RestEndpoints.USER)
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(RestEndpoints.GET_BY_USERID)
    public ResponseEntity<?> getAllTasksForUser(@PathVariable long userId,
                                                @SortDefault(sort = "name",caseSensitive = false)
                                                @PageableDefault(size = 20) Pageable pageable) {
        List<TaskResponse> taskResponseList = userService.getAllTasks(userId);
        return ResponseEntity.ok().body(taskResponseList);
    }
}

package com.example.Controller;

import com.example.config.RestEndpoints;
import com.example.model.Task;
import com.example.model.User;
import com.example.repository.TaskRepository;
import com.example.request.TaskShareRequest;
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

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(RestEndpoints.USER)
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

    @GetMapping(RestEndpoints.GET_BY_USERID)
    public ResponseEntity<?> getAllTasksForUser(HttpServletRequest request,
                                                @RequestParam(name = "sortBy", defaultValue = "title") String sortBy,
                                                @RequestParam(name = "pageNum", defaultValue = "0") Integer pageNum,
                                                @RequestParam(name = "size", defaultValue = "5") Integer pageSize) {
        Pageable sortByGivenColumn = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
        long userId = userService.getUserIdByToken(request).getUserId();
        List<TaskResponse> taskResponseList = userService.getAllTasks(userId, sortByGivenColumn);
        return ResponseEntity.ok().body(taskResponseList);
    }

    @PutMapping(RestEndpoints.SHARE_THE_TASK)
    public ResponseEntity<?> shareTheTask(@RequestParam(name = "taskId") long taskId,
                                          HttpServletRequest request) throws IllegalAccessException {
        User userId = userService.getUserIdByToken(request);
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NoSuchElementException("No task found with " + taskId));
        if (!task.getOwnerId().getUserId().equals(userId.getUserId())) {
            throw new IllegalAccessException("Only owner of the taskId: " + taskId + " can share.");
        }
        sharedTaskService.share();
        return null;
    }

}

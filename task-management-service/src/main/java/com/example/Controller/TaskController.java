package com.example.Controller;

import com.example.config.RestEndpoints;
import com.example.model.User;
import com.example.request.TaskRequest;
import com.example.request.TaskUpdateRequest;
import com.example.response.TaskResponse;
import com.example.service.TaskService;
import com.example.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RestEndpoints.TASKS)
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @PostMapping(RestEndpoints.CREATE_TASK)
    public ResponseEntity<?> createTask(@RequestBody @Valid TaskRequest taskRequest, HttpServletRequest httpServletRequest) {
        User owner = userService.getUserIdByToken(httpServletRequest);
        TaskResponse taskResponse = taskService.createTask(owner, taskRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskResponse);
    }

    @DeleteMapping(RestEndpoints.DELETE_TASK)
    public ResponseEntity<?> deleteTaskById(@PathVariable long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok().body("task has been deleted successfully");
    }

    @PutMapping(RestEndpoints.UPDATE_TASK)
    public ResponseEntity<?> updateTaskById(@RequestBody TaskUpdateRequest taskUpdateRequest, @PathVariable String taskId) {
        taskUpdateRequest.setId(Long.parseLong(taskId));
        TaskResponse taskResponse = taskService.updateTask(taskUpdateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskResponse);
    }

    @GetMapping(RestEndpoints.GET_TASK_BY_TASKID)
    public ResponseEntity<?> getTaskById(@PathVariable(name = "taskId") long taskId) {
        TaskResponse taskResponse= taskService.getTaskById(taskId);
        return ResponseEntity.status(HttpStatus.OK).body(taskResponse);
    }
}

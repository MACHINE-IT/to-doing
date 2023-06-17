package com.example.Controller;

import com.example.config.RestEndpoints;
import com.example.request.TaskRequest;
import com.example.response.TaskResponse;
import com.example.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestEndpoints.TASKS)
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(RestEndpoints.CREATE_TASK)
    public ResponseEntity<?> createTask(@RequestBody @Valid TaskRequest taskRequest) {
        TaskResponse taskResponse = taskService.createTask(taskRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskResponse);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTaskById(@PathVariable long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok().body("task has been deleted successfully");
    }
}

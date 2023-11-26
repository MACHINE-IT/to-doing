package com.example.Controller;

import com.example.config.RestEndpoints;
import com.example.model.User;
import com.example.request.*;
import com.example.response.ReminderResponse;
import com.example.response.TaskResponse;
import com.example.service.ReminderService;
import com.example.service.SharedTaskService;
import com.example.service.TaskService;
import com.example.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
    private final UserService userService;
    private final ReminderService reminderService;

    private final SharedTaskService sharedTaskService;


    @Autowired
    public TaskController(TaskService taskService, UserService userService, ReminderService reminderService, SharedTaskService sharedTaskService) {
        this.taskService = taskService;
        this.userService = userService;
        this.reminderService = reminderService;
        this.sharedTaskService = sharedTaskService;
    }

    @PostMapping(RestEndpoints.CREATE_TASK)
    public ResponseEntity<?> createTask(@RequestBody @Valid TaskRequest taskRequest, HttpServletRequest httpServletRequest) throws Exception {
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

    @PutMapping(RestEndpoints.ADD_CATEGORY_FROM_TASK)
    public ResponseEntity<?> addCategoryToTask(@PathVariable("taskId") Long taskId, @PathVariable("categoryId") Long categoryId, HttpServletRequest httpServletRequest) {
        User owner = userService.getUserIdByToken(httpServletRequest);
        try {
            taskService.addCategoryToTask(owner.getUserId(), taskId, categoryId);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body("task added to the category");
    }

    @DeleteMapping(RestEndpoints.REMOVE_CATEGORY_FROM_TASK)
    public ResponseEntity<?> removeCategoryFromTask(@PathVariable("taskId") Long taskId, @PathVariable("categoryId") Long categoryId, HttpServletRequest httpServletRequest) {
        User owner = userService.getUserIdByToken(httpServletRequest);
        try {
            taskService.removeCategoryFromTask(owner.getUserId(), taskId, categoryId);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body("task removed from the category");
    }

    @PostMapping(RestEndpoints.SET_REMINDER)
    public ResponseEntity<?> setReminder(@PathVariable("taskId") Long taskId, @RequestBody ReminderRequest reminderRequest, HttpServletRequest httpServletRequest) {
        User owner = userService.getUserIdByToken(httpServletRequest);
        ReminderResponse savedReminder = reminderService.setReminder(taskId, owner.getUserId(), reminderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReminder);
    }

    @GetMapping(RestEndpoints.GET_REMINDERS)
    public ResponseEntity<?> getReminders(@PathVariable("taskId") Long taskId, HttpServletRequest request) {
        User user  = userService.getUserIdByToken(request);
        List<ReminderResponse> reminders = reminderService.getReminders(taskId, user.getUserId());
        return ResponseEntity.ok().body(reminders);
    }

    @DeleteMapping(RestEndpoints.DELETE_REMINDER)
    public ResponseEntity<?> deleteReminders(@PathVariable("taskId") Long taskId, @PathVariable("reminderId") Long reminderId, HttpServletRequest request) {
        User user  = userService.getUserIdByToken(request);
        reminderService.deleteReminder(taskId, reminderId, user.getUserId());
        return ResponseEntity.ok().body("reminder deleted successfully");
    }

    @PutMapping(RestEndpoints.SHARE_THE_TASK)
    public ResponseEntity<?> shareTheTask(@PathVariable("taskId") Long taskId,@Valid @RequestBody TaskShareRequest taskShareRequest,
                                          HttpServletRequest request) {
        User owner = userService.getUserIdByToken(request);
        taskShareRequest.setTaskId(taskId);
        taskShareRequest.setTaskOwner(owner);
        sharedTaskService.share(taskShareRequest);
        return ResponseEntity.ok("task has been shared");
    }

    @DeleteMapping(RestEndpoints.UNSHARE_THE_TASK)
    public ResponseEntity<?> unshareTheTask(@PathVariable("taskId") Long taskId,@Valid @RequestBody TaskUnShareRequest taskUnShareRequest,
                                            HttpServletRequest request) {
        User owner = userService.getUserIdByToken(request);
        taskUnShareRequest.setTaskId(taskId);
        taskUnShareRequest.setTaskOwner(owner);

        sharedTaskService.unShare(taskUnShareRequest);

        return ResponseEntity.ok("Task has been unshared with selected users.");
    }


}

package com.example.service.impl;

import com.example.model.NotificationType;
import com.example.model.Task;
import com.example.model.User;
import com.example.repository.TaskRepository;
import com.example.repository.UserRepository;
import com.example.request.TaskShareRequest;
import com.example.response.NotificationDTO;
import com.example.service.SharedTaskService;
import com.example.service.UserNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class SharedTaskServiceImpl implements SharedTaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    private final UserNotificationService notificationService;

    @Autowired
    public SharedTaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository, UserNotificationService notificationService) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    @Override
    @Transactional
    public void share(TaskShareRequest taskShareRequest) {
        long taskId = taskShareRequest.getTaskId();
        Set<Long> newTaskMemberIds = taskShareRequest.getUserSet();
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NoSuchElementException("Task doesn't exist"));

        Set<User> newTaskMembers = newTaskMemberIds.stream()
                .map(each -> userRepository
                        .findById(each)
                        .orElseThrow(() ->
                                new NoSuchElementException(String.format("No user with userId: %s found", each))))
                .collect(Collectors.toSet());

        newTaskMembers.forEach(user -> {
            if(!isAlreadyMember(task, newTaskMembers) && !isOwnerTryingToShareHimself(task, newTaskMembers)) {
                task.getTaskMembers().addAll(newTaskMembers);
                taskRepository.save(task);
            }
        });
        List<NotificationDTO> usersToBeNotified = prepareNotificationDTO(task);
        notificationService.generateNotifications(usersToBeNotified);
    }

    private List<NotificationDTO> prepareNotificationDTO(Task task) {
        List<User> newMembers = task.getTaskMembers().stream().toList();
        List<User> subscribedMembers = notificationService.getSubscribedMembers(NotificationType.TASKS_SHARE);
        List<String> usernames = newMembers.stream().map(User::getUsername).toList();
        String title = String.format("New user added to %s", task.getTitle());
        String message = String.format("%s added %s to the %s",
                task.getOwnerId().getUsername(),
                String.join(", ", usernames),
                task.getTitle()
        );
        List<NotificationDTO> usersToBeNotified = new ArrayList<>();
        for (User subscribedUser:subscribedMembers) {
            NotificationDTO notificationDTO = NotificationDTO.builder()
                    .title(title)
                    .message(message)
                    .recipientUsername(subscribedUser.getUsername())
                    .createdAt(LocalDateTime.now())
                    .build();
            usersToBeNotified.add(notificationDTO);
        }
        return usersToBeNotified;
    }

    public boolean isAlreadyMember(Task task, Set<User> newTaskMembers) {
        return newTaskMembers.stream().anyMatch(user -> task.getTaskMembers().contains(user));
    }

    private boolean isOwnerTryingToShareHimself(Task task, Set<User> newTaskMembers) {
        return newTaskMembers.stream().anyMatch(user -> task.getOwnerId().equals(user));
    }

    @Override
    public void unShare(TaskShareRequest taskShareRequest) {

    }
}

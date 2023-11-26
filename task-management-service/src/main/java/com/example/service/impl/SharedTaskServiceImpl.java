package com.example.service.impl;

import com.example.model.NotificationType;
import com.example.model.Task;
import com.example.model.User;
import com.example.repository.TaskRepository;
import com.example.repository.UserRepository;
import com.example.request.TaskShareRequest;
import com.example.request.TaskUnShareRequest;
import com.example.response.NotificationDTO;
import com.example.service.SharedTaskService;
import com.example.service.UserNotificationService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    private boolean isUserAuthorized(Long taskId,Long userId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"))
                .getOwner().getUserId().equals(userId);
    }

    @Override
    @Transactional
    public void share(TaskShareRequest taskShareRequest) {
        long taskId = taskShareRequest.getTaskId();
        List<Long> newTaskMemberIds = taskShareRequest.getUserSet().stream().toList();
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NoSuchElementException("Task doesn't exist"));
        Long taskOwner = taskShareRequest.getTaskOwner().getUserId();
        boolean isAlreadyMember = taskRepository.existsSharedTaskForUsers(newTaskMemberIds, taskId);


        boolean isOwnerTryingToShareWithHimself = taskRepository.isOwnerTryingToShareToHimself(taskId, newTaskMemberIds);
        if(!isUserAuthorized(taskId, taskOwner)) {
            throw new RuntimeException("User is not authorized to share the task");
        } else {
            Set<User> newTaskMembers = newTaskMemberIds.stream()
                    .map(each -> userRepository
                            .findById(each)
                            .orElseThrow(() ->
                                    new NoSuchElementException(String.format("No user with userId: %s found", each))))
                    .collect(Collectors.toSet());


            try {
                if(isAlreadyMember) {
                    throw new RuntimeException("some users is already a member. Request failed please check.");
                }
                if(isOwnerTryingToShareWithHimself) {
                    throw new RuntimeException("you cannot share the task to yourself");
                } else {
                    task.getSharedWithUsers().addAll(newTaskMembers);
                }
            } catch (DataIntegrityViolationException e) {
                String errorMessage = extractErrorMessageFromDataIntegrityViolationException(e);
                throw new RuntimeException(errorMessage, e);
            }


//            });
//            List<NotificationDTO> usersToBeNotified = prepareNotificationDTO(task);
//            notificationService.generateNotifications(usersToBeNotified);
        }
    }
    private String extractErrorMessageFromDataIntegrityViolationException(DataIntegrityViolationException e) {
        if (e.getCause() instanceof ConstraintViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) e.getCause();
            // Further inspection and specific error message construction
            return "Constraint violation: " + cve.getMessage();
        }
        return "Data integrity violation occurred";
    }

    private List<NotificationDTO> prepareNotificationDTO(Task task) {
        List<User> newMembers = task.getSharedWithUsers().stream().toList();
        List<User> subscribedMembers = notificationService.getSubscribedMembers(NotificationType.TASKS_SHARE);
        List<String> usernames = newMembers.stream().map(User::getUsername).toList();
        String title = String.format("New user added to %s", task.getTitle());
        String message = String.format("%s added %s to the %s",
                task.getOwner().getUsername(),
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


    private boolean isOwnerTryingToShareHimself(Task task, Set<User> newTaskMembers) {
        return newTaskMembers.stream().anyMatch(user -> task.getOwner().getUserId().equals(user.getUserId()));
    }

    @Override
    @Transactional
    public void unShare(TaskUnShareRequest taskUnShareRequest) {
        Long taskId = taskUnShareRequest.getTaskId();
        List<Long> unShareUserIds = taskUnShareRequest.getUserToBeUnshared().stream().toList();
        Task task = taskRepository.findSharedWithUsersByTaskId(taskId).orElseThrow(() -> new NoSuchElementException("Task doesn't exist"));
        Long taskOwner = taskUnShareRequest.getTaskOwner().getUserId();
        Set<User> taskOldMembers = task.getSharedWithUsers();
        if (!isUserAuthorized(taskId, taskOwner)) {
            throw new RuntimeException("User is not authorized to unshare the task");
        }

        // Check if the users to be unshared are currently members of the shared task
        boolean areUsersCurrentlyMembers = taskRepository.existsSharedTaskForUsers(unShareUserIds, taskId);

        if (!areUsersCurrentlyMembers) {
            throw new RuntimeException("Some users are not currently members of the task. Unshare request failed.");
        }

        List<User> unShareableUsers = userRepository.findAllById(unShareUserIds);

        for(User user: unShareableUsers) {
            user.getSharedTasks().removeIf(sharedTask -> sharedTask.getId().equals(taskId));
            userRepository.save(user);
        }

//        List<NotificationDTO> usersToBeNotified = prepareUnshareNotificationDTO(task, unshareUserIds);
//        notificationService.generateNotifications(usersToBeNotified);
    }

    private List<NotificationDTO> prepareUnshareNotificationDTO(Task task, Set<Long> unshareUserIds) {
        // Prepare notification messages for users who are unshared from the task
        List<String> unsharedUsernames = userRepository.findAllById(unshareUserIds)
                .stream()
                .map(User::getUsername)
                .collect(Collectors.toList());

        String title = String.format("Users removed from %s", task.getTitle());
        String message = String.format("%s removed %s from the %s",
                task.getOwner().getUsername(),
                String.join(", ", unsharedUsernames),
                task.getTitle()
        );

        List<NotificationDTO> usersToBeNotified = new ArrayList<>();
        for (User sharedUser : task.getSharedWithUsers()) {
            if (unshareUserIds.contains(sharedUser.getUserId())) {
                NotificationDTO notificationDTO = NotificationDTO.builder()
                        .title(title)
                        .message(message)
                        .recipientUsername(sharedUser.getUsername())
                        .createdAt(LocalDateTime.now())
                        .build();
                usersToBeNotified.add(notificationDTO);
            }
        }

        return usersToBeNotified;
    }

}

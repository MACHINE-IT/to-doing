package com.example.service;

import com.example.model.NotificationType;
import com.example.model.User;
import com.example.response.NotificationDTO;

import java.util.List;


public interface UserNotificationService {

    List<NotificationDTO> generateNotifications(List<NotificationDTO> usersToBeNotified);

    List<User> getSubscribedMembers(NotificationType notificationType);

}

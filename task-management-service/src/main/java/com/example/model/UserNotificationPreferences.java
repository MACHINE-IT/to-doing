package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "user_notification_preferences",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "notification_type"})
})
public class UserNotificationPreferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long notificationPreferencesId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated
    @Column(name = "notification_type")
    private NotificationType notificationType;

    @Column(name = "enabled")
    private boolean isEnabled;
}

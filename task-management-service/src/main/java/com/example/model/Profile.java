package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Profile {

    @Id
    long profileId;

    @OneToOne
    private User userId;

    private String bio;

    private long tasksCompleted;

    private long tasksInProgress;

    private double successRate;

    private boolean isPersonalNotificationEnabled;

    private boolean isPrivateNotificationEnabled;
}

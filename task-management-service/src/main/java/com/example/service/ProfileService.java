package com.example.service;

public interface ProfileService {
    public void calculateSuccessRate(long userId);

    public void createNewProfile();

    public void updateTasksCompleted(long userId);

    public void updateSuccessRate(long userId);

    public void updateBio(long userId);

}

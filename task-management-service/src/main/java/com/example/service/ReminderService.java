package com.example.service;

import com.example.model.Reminder;
import com.example.request.ReminderRequest;
import com.example.response.ReminderResponse;

import java.util.List;
public interface ReminderService {

    /**
     * Sets a reminder for a task.
     *
     * @param taskId the ID of the task to set the reminder for
     * @return the created or updated reminder
     */
    ReminderResponse setReminder(Long taskId, Long userId, ReminderRequest reminderRequest);

    /**
     * Retrieves reminders for a task.
     *
     * @param taskId the ID of the task to get reminders for
     * @return the reminders associated with the task
     */
    public List<ReminderResponse> getReminders(Long userId, Long taskId);

    /**
     * Updates an existing reminder for a task.
     *
     * @param taskId the ID of the task the reminder belongs to
     * @param reminderId the ID of the reminder to update
     * @param updatedReminder the updated reminder details
     * @return the updated reminder
     */
    Reminder updateReminder(Long taskId, Long reminderId, Reminder updatedReminder);

    /**
     * Deletes a reminder from a task.
     *
     * @param taskId the ID of the task the reminder belongs to
     * @param reminderId the ID of the reminder to delete
     */

    void deleteReminder(Long taskId, Long reminderId, Long userId);
}

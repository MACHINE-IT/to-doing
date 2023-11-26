package com.example.response;

import com.example.model.*;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskResponse implements Serializable {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime dueDate;

    private CategoryTable category;

    private LocalDateTime creationDate;

    private LocalDateTime completionDate;

    private boolean isImportant = false;

    private TaskStatus taskStatus = TaskStatus.PENDING;

    private List<Reminder> reminders;
}
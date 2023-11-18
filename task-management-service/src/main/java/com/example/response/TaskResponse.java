package com.example.response;

import com.example.model.Category;
import com.example.model.Priority;
import com.example.model.TaskStatus;
import com.example.model.User;
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

    private Category category;
    private List<String> members;

    private LocalDateTime creationDate;

    private LocalDateTime completionDate;

    private boolean isImportant = false;

    private TaskStatus taskStatus = TaskStatus.PENDING;

    private LocalDateTime reminder;
}
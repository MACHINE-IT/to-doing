package com.example.request;


import com.example.model.Category;
import com.example.model.Priority;

import com.example.model.TaskStatus;
import com.example.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequest {
    @NotBlank(message = "title should not be blank")
    private String title;

    private String description;

    private LocalDateTime dueDate;

    @NotNull(message = "category should not be blank")
    private Category category;

    private User ownerId;

    private LocalDateTime creationDate;

    private LocalDateTime completionDate;

    @Builder.Default
    private boolean isImportant = false;

    @Builder.Default
    private TaskStatus taskStatus = TaskStatus.PENDING;

    @Column(name = "reminder")
    private LocalDateTime reminder;
}

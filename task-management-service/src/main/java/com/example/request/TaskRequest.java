package com.example.request;


import com.example.model.Category;
import com.example.model.Priority;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequest {
    @NotBlank(message = "title should not be blank")
    private String title;

    private String description;

    private LocalDateTime dueDate;

    private Priority priority;

    @NotNull(message = "category should not be blank")
    private Category category;

    @Min(value = 1, message = "please provide valid userId")
    private long userId;

}

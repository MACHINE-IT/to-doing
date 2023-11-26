package com.example.request;

import com.example.model.Task;
import com.example.model.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.Set;

@Data
@Builder
public class TaskShareRequest {

    User taskOwner;

    long taskId;

    @Size(min = 1, message = "Please add the userId's to share the task with")
            @UniqueElements(message = "userIds should be unique")
    Set<Long> userSet;
}

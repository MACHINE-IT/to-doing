package com.example.request;

import com.example.model.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;


@Data
public class TaskUnShareRequest {
    User taskOwner;

    Long taskId;

    @Size(min = 1, message = "Please add the userId's to unShare the task with")
    Set<Long> userToBeUnshared;
}

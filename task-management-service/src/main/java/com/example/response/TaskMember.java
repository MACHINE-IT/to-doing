package com.example.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskMember {
    long userId;
    String username;
}

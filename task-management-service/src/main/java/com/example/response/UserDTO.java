package com.example.response;

import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
}

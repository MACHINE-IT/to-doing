package com.example.request;

import com.example.annotations.ConfirmPassword;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ConfirmPassword(message = "password and confirmPassword must match")
public class UserRegisterRequest {

    @NotEmpty(message = "please provide a username")
    String username;

    @NotEmpty(message = "please provide an email")
    @Pattern(regexp = "^[A-Za-z0-9+.-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,}$", message = "please provide valid email")
    String email;

    @NotEmpty(message = "please provide firstName")
    String firstName;

    @NotEmpty(message = "please provide lastName")
    String lastName;

    @NotEmpty(message = "please provide a password")
    @Size(min = 8, max = 20, message = "password must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z0-9]+$", message = "password must contain both alphabets and numerics")
    String password;

    @NotEmpty(message = "please confirm the password")
    String confirmPassword;

}

package com.example.request;

import com.example.annotations.ValidEmailOrUsername;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSignInRequest {

    @ValidEmailOrUsername(message = "please provide email or username")
    String emailOrUsername;

    @NotEmpty(message = "please provide password")
    String password;
}



package com.example.annotations;

import com.example.validations.ValidEmailOrUsernameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidEmailOrUsernameValidator.class)
public @interface ValidEmailOrUsername {
    String message() default "Invalid email or username";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
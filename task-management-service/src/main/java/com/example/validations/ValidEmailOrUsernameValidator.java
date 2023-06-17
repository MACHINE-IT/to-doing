package com.example.validations;


import com.example.annotations.ValidEmailOrUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class ValidEmailOrUsernameValidator implements ConstraintValidator<ValidEmailOrUsername, String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) {
            return false;
        }

        return isValidEmail(value) || isValidUsername(value);
    }

    public boolean isValidEmail(String value) {
        if (value == null) {
            return false;
        }
        // Check if value is a valid email
        // Here's a basic example using a regular expression pattern

        // Regular expression for email validation
        String emailPattern = "^[A-Za-z0-9+.-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,}$";

        return value.matches(emailPattern);
    }

    public boolean isValidUsername(String value) {
        if (value == null) {
            return false;
        }
        // Check if value is a valid username
        // Implement your username validation logic here

        // Return true if value is a valid username, false otherwise
        return !value.isEmpty(); // Example: Assume any non-empty string is a valid username
    }

}
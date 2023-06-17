package com.example.validations;

import com.example.annotations.ConfirmPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, Object> {
    private String passwordFieldName;
    private String confirmPasswordFieldName;
    private String errorMessage;

    @Override
    public void initialize(ConfirmPassword constraintAnnotation) {
        errorMessage = constraintAnnotation.message();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null) {
            return true;
        }

        Class<?> clazz = value.getClass();
        try {
            Field passwordField = clazz.getDeclaredField("password");
            Field confirmPasswordField = clazz.getDeclaredField("confirmPassword");

            passwordField.setAccessible(true);
            confirmPasswordField.setAccessible(true);

            String password = (String) passwordField.get(value);
            String confirmPassword = (String) confirmPasswordField.get(value);

            if (password == null || !password.equals(confirmPassword)) {
                throw new IllegalArgumentException(errorMessage);
            }

            return true;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // Handle any exceptions, such as field not found or inaccessible
            return false;
        }
    }

}

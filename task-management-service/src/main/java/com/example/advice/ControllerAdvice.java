//package com.example.advice;
//
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import javax.naming.AuthenticationException;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestControllerAdvice
//@ComponentScan("com.example")
//public class ControllerAdvice {
//
//
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> exceptionHandler(MethodArgumentNotValidException exception) {
//        Map<String, String> errorMap = new HashMap<>();
//        exception.getBindingResult().getFieldErrors().forEach(error -> {
//            errorMap.put(error.getField(), error.getDefaultMessage());
//        });
//        return ResponseEntity.badRequest().body(errorMap);
//    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<?> exceptionHandler(IllegalArgumentException exception) {
//        return ResponseEntity.badRequest().body(exception.getMessage());
//    }
//
//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<?> badCredentialsExceptionHandler(BadCredentialsException exception) {
//        return ResponseEntity.badRequest().body(exception.getMessage());
//    }
//
//    @ExceptionHandler(AuthenticationException.class)
//    public ResponseEntity<?> signatureExceptionHandler(AuthenticationException authenticationException) {
//        return ResponseEntity.badRequest().body(authenticationException.getMessage());
//    }
//
//    @ExceptionHandler(io.jsonwebtoken.security.SignatureException.class)
//    public ResponseEntity<?> signatureExceptionHandler(io.jsonwebtoken.security.SignatureException exception) {
//        return ResponseEntity.badRequest().body(exception.getMessage());
//    }
//
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<String> accessDeniedExceptionHandler(AccessDeniedException exception) {
//        String message = "Access denied: " + exception.getMessage();
//        System.out.println(message);
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> exceptionHandler(Exception exception) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
//    }
//
//
//    @ExceptionHandler(SQLException.class)
//    public ResponseEntity<String> sqlExceptionHandler(SQLException exception) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
//    }
//
//
//}

package com.example.advice;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@ComponentScan("com.example")
public class ControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> exceptionHandler(MethodArgumentNotValidException exception) {
        logger.info("Handling MethodArgumentNotValidException: {}", exception.getMessage());
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errorMap);
    }

    @ExceptionHandler(jakarta.validation.ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException exception) {
        String errorMessage = "Validation error: " + exception.getCause().getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> badCredentialsExceptionHandler(BadCredentialsException exception) {
        logger.info("Handling BadCredentialsException: {}", exception.getMessage());
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> signatureExceptionHandler(AuthenticationException authenticationException) {
        logger.info("Handling AuthenticationException: {}", authenticationException.getMessage());
        return ResponseEntity.badRequest().body(authenticationException.getMessage());
    }

    @ExceptionHandler(io.jsonwebtoken.security.SignatureException.class)
    public ResponseEntity<?> signatureExceptionHandler(io.jsonwebtoken.security.SignatureException exception) {
        logger.info("Handling SignatureException: {}", exception.getMessage());
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> accessDeniedExceptionHandler(AccessDeniedException exception) {
        String message = "Access denied: " + exception.getMessage();
        logger.info("Handling AccessDeniedException: {}", message);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> sqlExceptionHandler(SQLIntegrityConstraintViolationException exception) {
        logger.error("Handling SQLException: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("some internal server error occured");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> exceptionHandler(IllegalArgumentException exception) {
        logger.error("Handling Exception: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> noSuchElementExceptionHandler(NoSuchElementException exception) {
        logger.error("Handling Exception: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        logger.error("Handling Exception: {}", exception.getCause().getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("some internal server error ocurred");
    }


}

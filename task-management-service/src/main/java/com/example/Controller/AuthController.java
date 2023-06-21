package com.example.Controller;

import com.example.config.RestEndpoints;
import com.example.request.UserRegisterRequest;
import com.example.request.UserSignInRequest;
import com.example.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;

@RestController
@RequestMapping(RestEndpoints.AUTH)
public class AuthController {


    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(RestEndpoints.REGISTER)
    public ResponseEntity<?> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) throws SQLException {
        String token = authService.registerUser(userRegisterRequest);
        return ResponseEntity.ok().body("you are registered successfully");
    }

    @PostMapping(RestEndpoints.SIGN_IN)
    public ResponseEntity<?> signIn(@RequestBody @Valid UserSignInRequest userSignInRequest) {
        String token = authService.signIn(userSignInRequest);
        return ResponseEntity.ok().body(token);
    }

    @GetMapping("/signing")
    public String signIn() {
        return "redirect:/oauth2/authorization/google";
    }

    public void signOut() {

    }

    @GetMapping(RestEndpoints.RESET_PASSWORD)
    public ResponseEntity<?> resetPassword(String email) {
        authService.forgotPassword(email);
        return ResponseEntity.ok().body("reset password link has been sent to your email");
    }
}

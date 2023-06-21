package com.example.service;

import com.example.model.User;

public interface ResetPasswordTokenService {

    String generatePasswordTokenForUser();

    boolean validateTheToken(String token);
}

package com.example.service;

import com.example.model.User;

public interface ResetPasswordTokenService {

    void generatePasswordTokenForUser(User user, String token);
}

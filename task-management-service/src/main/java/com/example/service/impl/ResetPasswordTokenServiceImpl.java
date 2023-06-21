package com.example.service.impl;

import com.example.model.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ResetPasswordTokenServiceImpl implements com.example.service.ResetPasswordTokenService {
    @Override
    public String generatePasswordTokenForUser() {
        return UUID.randomUUID().toString();
    }



    @Override
    public boolean validateTheToken(String token) {
        return false;
    }

}

package com.example.service.impl;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class ResetPasswordTokenServiceImpl implements com.example.service.ResetPasswordTokenService {
    @Override
    public String generatePasswordTokenForUser() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Date calculateExpirationDate(long min) {
        return Date.from(Instant.now().plusSeconds(60 * min));
    }


    @Override
    public boolean validateTheToken(String token) {
        return false;
    }

}

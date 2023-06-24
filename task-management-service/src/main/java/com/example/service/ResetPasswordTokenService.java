package com.example.service;

import org.springframework.stereotype.Service;

import java.util.Date;


public interface ResetPasswordTokenService {

    String generatePasswordTokenForUser();

    Date calculateExpirationDate(long mins);

    boolean validateTheToken(String token);
}

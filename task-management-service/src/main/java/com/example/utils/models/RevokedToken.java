package com.example.utils.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class RevokedToken {
    private String token;
    private Instant expirationTime;
}

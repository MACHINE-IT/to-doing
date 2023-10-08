package com.example.utils;

import com.example.utils.models.RevokedToken;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class TokenBlacklistService {

    private Set<RevokedToken> revokedTokens = new TreeSet<>((token1, token2) ->
            token1.getExpirationTime().compareTo(token2.getExpirationTime()));

    public void addRevokedTokens(String token, Instant expirationTime) {
        revokedTokens.add(new RevokedToken(token, expirationTime))  ;
    }

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public TokenBlacklistService() {
        executorService.scheduleAtFixedRate(this::cleanUpTokens, 0, 12, TimeUnit.HOURS);
    }

    public boolean isRevokedToken(String token) {
        return revokedTokens.stream().anyMatch(rt -> rt.getToken().equals(token));
    }

    public void cleanUpTokens() {
        Instant currentTime = Instant.now();
        revokedTokens.removeIf(rt -> rt.getExpirationTime().isBefore(currentTime));
    }
}

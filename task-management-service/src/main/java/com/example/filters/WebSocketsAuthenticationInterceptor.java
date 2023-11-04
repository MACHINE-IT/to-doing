package com.example.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WebSocketsAuthenticationInterceptor implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.info("request is on preSend");
        boolean isAuthenticated = checkAuthentication(message, channel);
        String token = null;
        if(isAuthenticated) {
            return ChannelInterceptor.super.preSend(message, channel);
        }
        try{
            log.info("blocking you..");
            throw new BadCredentialsException("nahi bhai nahi");
        } catch (BadCredentialsException e) {
            log.info("exception agaya bhai "+ e.getMessage());
        }
        return null;
    }

    private boolean checkAuthentication(Message<?> message, MessageChannel channel) {
        return false;
    }
}

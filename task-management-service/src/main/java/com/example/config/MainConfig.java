package com.example.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({ SecurityConfig.class, MailConfig.class, WebSocketsConfig.class})
public class MainConfig {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}

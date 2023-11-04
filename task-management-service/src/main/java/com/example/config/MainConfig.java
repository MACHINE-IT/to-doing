package com.example.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CorsConfig.class, SecurityConfig.class, MailConfig.class, RabbitConfig.class, WebSocketsConfig.class})
public class MainConfig {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}

package com.example.config;

import com.example.exceptions.JwtAuthenticationException;
import com.example.filters.JwtAuthorizationFilter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final String[] allowedUrls = {"/api/auth/**", "/test/**",
            "/v3/api-docs/**", "/swagger-ui/**",
            "/task-management-sockets/**"
    };

    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    private final JwtAuthenticationException jwtAuthenticationException;

    @Autowired
    public SecurityConfig(JwtAuthorizationFilter jwtAuthorizationFilter, JwtAuthenticationException jwtAuthenticationException) {
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
        this.jwtAuthenticationException = jwtAuthenticationException;
    }

    @Bean
    public SecurityFilterChain config(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers(allowedUrls).permitAll()
                            .anyRequest().authenticated();
                })
//                auth -> auth.loginPage("/signing").defaultSuccessUrl("/home")
                .oauth2Login(Customizer.withDefaults())
                .exceptionHandling(exceptionHandling -> {
                    exceptionHandling.authenticationEntryPoint(jwtAuthenticationException);
                })
                .build();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("HEAD",
                "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

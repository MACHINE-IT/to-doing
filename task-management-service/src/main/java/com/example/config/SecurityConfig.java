package com.example.config;

import com.example.exceptions.JwtAuthenticationException;
import com.example.filters.JwtAuthorizationFilter;
import com.example.service.ResetPasswordTokenService;
import com.example.service.impl.ResetPasswordTokenServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Properties;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final String[] allowedUrls = {"/api/auth/**", "/test/**", "/v3/api-docs/**", "/swagger-ui/**"};
    @Value("${spring.mail.username}")
    protected String username;
    {
        username = "";
    }

    @Value("${spring.mail.password}")
    protected String password;
    {
        password = "";
    }

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
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public SimpleMailMessage simpleMailMessage() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
         simpleMailMessage.setFrom("sandeepgfgjava@gmail.com");
         return simpleMailMessage;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

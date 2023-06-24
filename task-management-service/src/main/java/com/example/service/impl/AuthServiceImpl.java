package com.example.service.impl;

import com.example.model.PasswordToken;
import com.example.model.User;
import com.example.repository.ResetPasswordRepository;
import com.example.repository.UserRepository;
import com.example.request.UserRegisterRequest;
import com.example.request.UserSignInRequest;
import com.example.service.AuthService;
import com.example.service.MailService;
import com.example.service.ResetPasswordTokenService;
import com.example.utils.CustomAuthenticationProvider;
import com.example.utils.JwtService;
import com.example.validations.ValidEmailOrUsernameValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final ValidEmailOrUsernameValidator validator;
    private final PasswordEncoder passwordEncoder;

    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final MailService mailService;

    private final ResetPasswordTokenService resetPasswordTokenService;
    private final ResetPasswordRepository resetPasswordRepository;

    @Autowired
    public AuthServiceImpl(JwtService jwtService, ValidEmailOrUsernameValidator validator, PasswordEncoder passwordEncoder, CustomAuthenticationProvider customAuthenticationProvider,
                           UserRepository userRepository, ModelMapper modelMapper, MailServiceImpl mailService, ResetPasswordTokenServiceImpl resetPasswordTokenService,
                           ResetPasswordRepository resetPasswordRepository) {
        this.jwtService = jwtService;
        this.validator = validator;
        this.passwordEncoder = passwordEncoder;
        this.customAuthenticationProvider = customAuthenticationProvider;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.mailService = mailService;
        this.resetPasswordTokenService = resetPasswordTokenService;
        this.resetPasswordRepository = resetPasswordRepository;
    }

    @Override
    public String signIn(UserSignInRequest userSignInRequest) {
        if(checkAuthentication(userSignInRequest.getEmailOrUsername())) {
            return "you are already signed in";
        }
        String emailOrUsername = userSignInRequest.getEmailOrUsername();
        setAuthentication(emailOrUsername, userSignInRequest.getPassword());
        System.out.println("received the request"+userSignInRequest.toString());
        String token = jwtService.generateToken(emailOrUsername);
        System.out.println("is validated " + validator.toString());

        return token;
    }

    public void setAuthentication(String username, String password) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticated = customAuthenticationProvider.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authenticated);
    }

    private boolean checkAuthentication(String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated()
                && authentication.getName().equals(username);
    }

    @Override
    public void signOut() {

    }

    @Override
    public String registerUser(UserRegisterRequest userRegisterRequest) throws IllegalArgumentException {
        checkUserAvailabilityInDatabase(userRegisterRequest);
        String encodedPassword = passwordEncoder.encode(userRegisterRequest.getPassword());
        User user =  modelMapper.map(userRegisterRequest, User.class);

        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "user has been registered successfully";
    }

    public void checkUserAvailabilityInDatabase(UserRegisterRequest userRegisterRequest) throws IllegalArgumentException {
        String email = userRegisterRequest.getEmail();
        String username = userRegisterRequest.getUsername();
        if(userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("email already taken");
        }
        if(userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("username already taken");
        }
    }

    @Override
    public void authenticate() {

    }

    @Override
    public void forgotPassword(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        if(!userRepository.existsByEmail(email) || user.isEmpty()) {
            throw new IllegalArgumentException("given email is not registered with us");
        }
        String generatedToken = resetPasswordTokenService.generatePasswordTokenForUser();
        PasswordToken passwordToken = PasswordToken.builder()
                .token(generatedToken)
                .user(user.get())
                .expiryDate(resetPasswordTokenService.calculateExpirationDate(5L))
                .build();
        resetPasswordRepository.save(passwordToken);
        String body = String.format("click on the link to reset your password. %s", generateResetUrl().toString());
        mailService.send(user.get().getEmail(), "reset password", body);
    }

    public UriComponents generateResetUrl() {
        return UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/reset-password")
                .path("/"+resetPasswordTokenService.generatePasswordTokenForUser())
                .build();
    }

    @Override
    public void forgotUsername() {

    }

}

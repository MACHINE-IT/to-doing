package com.example.utils;

import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.validations.ValidEmailOrUsernameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final ValidEmailOrUsernameValidator validator;

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomAuthenticationProvider(ValidEmailOrUsernameValidator validator, UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.validator = validator;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String emailOrUsername = authentication.getName();
        String password = authentication.getCredentials().toString();
        User userDetails = emailOrUsernameLookUp(emailOrUsername);
        return checkPassword(password, userDetails);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public User emailOrUsernameLookUp(String emailOrUsername) {
        return getUser(emailOrUsername, validator, userRepository);
    }

    static User getUser(String emailOrUsername, ValidEmailOrUsernameValidator validator, UserRepository userRepository) {
        if(validator.isValidUsername(emailOrUsername)
                && userRepository.existsByUsername(emailOrUsername)) {
            return userRepository.findUserByUsername(emailOrUsername)
                    .orElseThrow(() -> new UsernameNotFoundException("given email or username is not registered"));
        }
        if(validator.isValidEmail(emailOrUsername) && userRepository.existsByEmail(emailOrUsername)) {
            return userRepository.findUserByEmail(emailOrUsername)
                    .orElseThrow(() -> new UsernameNotFoundException("given email or username is not registered"));
        }
        else throw new BadCredentialsException("User is not registered");
    }

    private UsernamePasswordAuthenticationToken checkPassword(String password, User user) {
        if(passwordEncoder.matches(password, user.getPassword())) {
            List<GrantedAuthority> authorityList = new ArrayList<>();
            authorityList.add(new SimpleGrantedAuthority("default"));
//            UserDetails principal = new org.springframework.security.core.userdetails.User(
//                    user.getUsername(), user.getPassword(), authorityList
//            );
            return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), authorityList);
        } else throw new BadCredentialsException("invalid credentails");
    }

}

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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.example.utils.CustomAuthenticationProvider.getUser;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final ValidEmailOrUsernameValidator validator;

    private final UserRepository userRepository;

    private final JwtService jwtService;


    @Autowired
    public JwtAuthenticationProvider(ValidEmailOrUsernameValidator validator, UserRepository userRepository, JwtService jwtService) {
        this.validator = validator;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
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

    private UsernamePasswordAuthenticationToken checkPassword(String password, User user) {
        if(password.equals(user.getPassword())) {
            List<GrantedAuthority> authorityList = new ArrayList<>();
            authorityList.add(new SimpleGrantedAuthority("default"));
//            UserDetails principal = new org.springframework.security.core.userdetails.User(
//                    user.getUsername(), user.getPassword(), authorityList
//            );
            return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), authorityList);
        } else throw new BadCredentialsException("invalid credentails");
    }
}

package com.example.service;

import com.example.request.UserRegisterRequest;
import com.example.request.UserSignInRequest;
import org.springframework.web.util.UriComponents;

import java.sql.SQLException;

public interface AuthService {

    public String signIn(UserSignInRequest userSignInRequest);

    public void signOut();

    public String registerUser(UserRegisterRequest userRegisterRequest) throws SQLException;

    public void authenticate();

    public void forgotPassword(String email);

    public void forgotUsername();

    public UriComponents generateResetUrl();
}

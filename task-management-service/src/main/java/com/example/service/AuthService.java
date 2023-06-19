package com.example.service;

import com.example.request.UserRegisterRequest;
import com.example.request.UserSignInRequest;

import java.sql.SQLException;

public interface AuthService {

    public String signIn(UserSignInRequest userSignInRequest);

    public void signOut();

    public String registerUser(UserRegisterRequest userRegisterRequest) throws SQLException;

    public void authenticate();

    public void forgotPassword(String email);

    public void forgotUsername();


}

package com.khan.code.taskify_rest_api.service;

import com.khan.code.taskify_rest_api.request.AuthenticationRequest;
import com.khan.code.taskify_rest_api.request.RegisterRequest;
import com.khan.code.taskify_rest_api.response.AuthenticationResponse;

public interface AuthenticationService {

    void register(RegisterRequest input) throws Exception;

    AuthenticationResponse login(AuthenticationRequest input);
}

package com.khan.code.taskify_rest_api.service;

import com.khan.code.taskify_rest_api.request.RegisterRequest;

public interface AuthenticationService {

    void register(RegisterRequest input) throws Exception;
}

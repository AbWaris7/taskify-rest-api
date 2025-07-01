package com.khan.code.taskify_rest_api.service;

import com.khan.code.taskify_rest_api.response.UserResponse;

public interface UserService {

    UserResponse getUserInfo();
    void deleteUser();
}

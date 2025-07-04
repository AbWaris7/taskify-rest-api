package com.khan.code.taskify_rest_api.controller;

import com.khan.code.taskify_rest_api.response.UserResponse;
import com.khan.code.taskify_rest_api.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User REST API Endpoints", description = "Operations related to ino about current user")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    public UserResponse getUserInfo() {

        return userService.getUserInfo();
    }

    @DeleteMapping
    public void deleteUser() {
        userService.deleteUser();
    }
}

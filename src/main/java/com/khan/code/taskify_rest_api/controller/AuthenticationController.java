package com.khan.code.taskify_rest_api.controller;


import com.khan.code.taskify_rest_api.request.AuthenticationRequest;
import com.khan.code.taskify_rest_api.request.RegisterRequest;
import com.khan.code.taskify_rest_api.response.AuthenticationResponse;
import com.khan.code.taskify_rest_api.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication REST API Endpoints", description = "Operation related to register & login")
public class AuthenticationController {

    private final  AuthenticationService authenticationService;
    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "Register a user", description = "create a new user in database")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public void register(@Valid  @RequestBody RegisterRequest registerRequest) throws Exception{

        authenticationService.register(registerRequest);
    }

    @Operation(summary = "Login a user", description = "submit email & password to authentication user")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public AuthenticationResponse login(@Valid @RequestBody AuthenticationRequest authenticationRequest) {

        return authenticationService.login(authenticationRequest);
    }
}

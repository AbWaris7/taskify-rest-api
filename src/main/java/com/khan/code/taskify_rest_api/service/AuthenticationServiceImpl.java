package com.khan.code.taskify_rest_api.service;

import com.khan.code.taskify_rest_api.entity.Authority;
import com.khan.code.taskify_rest_api.entity.User;
import com.khan.code.taskify_rest_api.repository.UserRepository;
import com.khan.code.taskify_rest_api.request.AuthenticationRequest;
import com.khan.code.taskify_rest_api.request.RegisterRequest;
import com.khan.code.taskify_rest_api.response.AuthenticationResponse;
import io.jsonwebtoken.Jwts;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    @Transactional
    public void register(RegisterRequest input) throws Exception {

        if(isEmailTaken(input.getEmail())) {
            throw new Exception("Email is taken");
        }
        User user = buildNewUser(input);
        userRepository.save(user);

    }

    @Override
    @Transactional
    public AuthenticationResponse login(AuthenticationRequest input) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword())
        );

        User user = userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        String jwtToken = jwtService.generateToken(new HashMap<>(), user);
        return new AuthenticationResponse(jwtToken);
    }

    private boolean isEmailTaken(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private List<Authority> initialAuthority() {

        boolean isFirstUser = userRepository.count() == 0;
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority("ROLE_EMPLOYEE"));
        if (isFirstUser) {

            authorities.add(new Authority("ROLE_ADMIN"));
        }
        return authorities;
    }

    private User buildNewUser(RegisterRequest input) {

        User user = new User();
        user.setId(0);
        user.setFirstName(input.getFirstName());
        user.setLastName(input.getLastName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setAuthorities(initialAuthority());
        return user;
    }
}

package com.khan.code.taskify_rest_api.service;

import com.khan.code.taskify_rest_api.entity.User;
import com.khan.code.taskify_rest_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User getUserInfo() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymouseUser")) {
            throw new AccessDeniedException("You are not authorized to perform this action.");

        }

        return (User) authentication.getPrincipal();
    }
}

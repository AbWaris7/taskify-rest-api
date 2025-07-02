package com.khan.code.taskify_rest_api.util;

import com.khan.code.taskify_rest_api.entity.User;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class FindAuthenticatedUserImpl implements FindAuthenticateUser{
    @Override
    public User getAuthenticateUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymouseUser")) {
            throw new AccessDeniedException("You are not authorized to perform this action.");

        }

       return  (User) authentication.getPrincipal();

    }
}

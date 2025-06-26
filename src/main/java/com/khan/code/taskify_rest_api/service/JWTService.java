package com.khan.code.taskify_rest_api.service;

import org.springframework.security.core.userdetails.UserDetails;
import java.util.Map;

public interface JWTService {

    /**
     * Extracts username (typically email) from the JWT token.
     */
    String extractUsername(String token);

    /**
     * Validates the token against user details.
     */
    boolean isTokenValid(String token, UserDetails userDetails);

    /**
     * Generates a JWT token with additional custom claims.
     */
    String generateToken(Map<String, Object> claims, UserDetails userDetails);

}

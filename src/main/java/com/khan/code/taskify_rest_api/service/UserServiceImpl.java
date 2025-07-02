package com.khan.code.taskify_rest_api.service;

import com.khan.code.taskify_rest_api.entity.Authority;
import com.khan.code.taskify_rest_api.entity.User;
import com.khan.code.taskify_rest_api.repository.UserRepository;
import com.khan.code.taskify_rest_api.response.UserResponse;
import com.khan.code.taskify_rest_api.util.FindAuthenticateUser;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final FindAuthenticateUser findAuthenticateUser;

    public UserServiceImpl(UserRepository userRepository, FindAuthenticateUser findAuthenticateUser) {
        this.userRepository = userRepository;
        this.findAuthenticateUser = findAuthenticateUser;
    }

    @Override
    @Transactional
    public UserResponse getUserInfo() {

        User user = findAuthenticateUser.getAuthenticateUser();
        return new UserResponse(
                user.getId(), user.getFirstName() +" " + user.getLastName(), user.getEmail(), user.getAuthorities().stream().map(auth -> (Authority) auth).toList());

    }

    @Override
    @Transactional
    public void deleteUser() {

        User user = findAuthenticateUser.getAuthenticateUser();
        if(isLastAdmin(user)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admin cannot delete itself.");
        }
        userRepository.delete(user);

    }

    private boolean isLastAdmin(User user) {
        boolean isAdmin = user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if(isAdmin) {

            long adminCount = userRepository.countAdminUsers();
            return adminCount <=1;
        }

        return false;
    }
}

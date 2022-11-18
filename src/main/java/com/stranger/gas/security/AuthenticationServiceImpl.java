/*package com.stranger.gas.security;

import com.stranger.gas.model.User;
import com.stranger.gas.service.UserService;
import org.springframework.stereotype.Service;
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private UserService userService;

    public AuthenticationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User register(String firstName, String lastName, String email, String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user = userService.create(user);
        return user;
    }
}*/

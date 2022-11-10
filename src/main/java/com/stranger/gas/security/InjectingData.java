package com.stranger.gas.security;

import com.stranger.gas.model.User;
import com.stranger.gas.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InjectingData {
    private UserService userService;
    private AuthenticationService authenticationService;

    public InjectingData(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostConstruct
    public void init() {
        User testUser1 = new User();
        testUser1.setEmail("test@gmail.com");
        testUser1.setPassword("1");
        userService.create(testUser1);
        User testUser2 = new User();
        testUser2.setFirstName("Ann");
        testUser2.setLastName("Nna");
        testUser2.setEmail("ann@gmail.com");
        testUser2.setPassword("1");
        authenticationService.register(testUser2.getFirstName(), testUser2.getLastName(), testUser2.getEmail(), testUser2.getPassword());
    }
}


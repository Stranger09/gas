package com.stranger.gas.service;

import com.stranger.gas.model.User;
/*import org.springframework.security.core.userdetails.UserDetailsService;*/

public interface UserService /*extends UserDetailsService*/ {
    User get(Long id);

    User update(User user);

    void delete(Long id);

    User create(User user);

    boolean isUniqueUsername(String email);

    User getByEmail(String email);
}


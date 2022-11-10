package com.stranger.gas.mapper;

import com.stranger.gas.dto.UserResponseDto;
import com.stranger.gas.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto mapFromUserToUserResponseDto(User user) {
        return new UserResponseDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    }
}

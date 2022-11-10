package com.stranger.gas.dto;

import com.stranger.gas.annotation.EmailConstraint;
import com.stranger.gas.annotation.PasswordConstraint;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @EmailConstraint
    @Unique
    private String email;
    @NotNull
    @PasswordConstraint
    private String password;
}

package com.jifs.backend.user.dto;

import com.jifs.backend.user.Role;

import lombok.Data;

@Data
public class UserUpdateDto {
    private String username;
    private Role role;
}

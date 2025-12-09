package com.jifs.backend.user.dto;

import com.jifs.backend.user.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String username;
    private Role role;
}

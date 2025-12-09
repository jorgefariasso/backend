package com.jifs.backend.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jifs.backend.user.dto.UserCreateDto;
import com.jifs.backend.user.dto.UserDto;
import com.jifs.backend.user.dto.UserUpdateDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserDto create(UserCreateDto dto) {
        if (repo.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email ya registrado");
        }

        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .role(dto.getRole())
                .build();

        repo.save(user);
        return toDto(user);
    }

    public Page<UserDto> findAll(int page, int size) {
        return repo.findAll(PageRequest.of(page, size))
                .map(this::toDto);
    }

    public UserDto update(Long id, UserUpdateDto dto) {
        User user = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
        }

        if (dto.getRole() != null) {
            user.setRole(dto.getRole());
        }

        repo.save(user);

        return toDto(user);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        repo.deleteById(id);
    }

    private UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        return dto;
    }

}

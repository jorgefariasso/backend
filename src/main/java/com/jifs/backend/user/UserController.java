package com.jifs.backend.user;

import org.springframework.web.bind.annotation.RestController;

import com.jifs.backend.user.dto.UserCreateDto;
import com.jifs.backend.user.dto.UserDto;
import com.jifs.backend.user.dto.UserUpdateDto;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService service;

    @PostMapping
    public UserDto create(@RequestBody UserCreateDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public Page<UserDto> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }
    
    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserUpdateDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

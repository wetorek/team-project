package com.wetorek.teamproject.controller;

import com.wetorek.teamproject.dto.UserResponseDto;
import com.wetorek.teamproject.mapper.UserMapper;
import com.wetorek.teamproject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/clients")
    @ResponseStatus(HttpStatus.OK)
    List<UserResponseDto> getAllClients() {
        var users = userService.getAllClients();
        return userMapper.mapListToDto(users);
    }
}

package com.wetorek.teamproject.service;

import com.wetorek.teamproject.dto.UserRegisterDto;
import com.wetorek.teamproject.entity.Role;
import com.wetorek.teamproject.entity.User;
import com.wetorek.teamproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegisterService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(UserRegisterDto userRegisterDto) {
        if (userRepository.existsByUsernameOrEmail(userRegisterDto.getUsername(), userRegisterDto.getEmail())) {
            throw new IllegalArgumentException("This credentials already exist");
        }
        var encodedPassword = passwordEncoder.encode(userRegisterDto.getPassword());
        var user = new User(userRegisterDto.getUsername(), encodedPassword, userRegisterDto.getName(), userRegisterDto.getSurname(),
                userRegisterDto.getEmail(), List.of(Role.ROLE_USER));
        userRepository.save(user);
    }
}

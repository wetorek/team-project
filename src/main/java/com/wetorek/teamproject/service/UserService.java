package com.wetorek.teamproject.service;

import com.wetorek.teamproject.entity.User;
import com.wetorek.teamproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}

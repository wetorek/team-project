package com.wetorek.teamproject.service;

import com.wetorek.teamproject.auth.AuthenticatedUser;
import com.wetorek.teamproject.entity.Role;
import com.wetorek.teamproject.entity.User;
import com.wetorek.teamproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public Optional<User> getCurrentUser() {
        var username = ((AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return userRepository.findUserByUsername(username);
    }

    public List<User> getAllClients() {
        return userRepository.findAll().stream()
                .filter(u -> textIfClient(u.getRoles()))
                .collect(Collectors.toList());
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    private boolean textIfClient(List<Role> roles) {
        return !roles.contains(Role.ROLE_ADMIN);
    }
}

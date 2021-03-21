package com.wetorek.teamproject;

import com.wetorek.teamproject.entity.Role;
import com.wetorek.teamproject.entity.User;
import com.wetorek.teamproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        var user1 = new User("user", passwordEncoder.encode("user"), "name", "surname", "mail", List.of(Role.ROLE_USER));
        var user2 = new User("user2", passwordEncoder.encode("user2"), "name", "surname", "mail", List.of(Role.ROLE_USER));
        var admin = new User("admin", passwordEncoder.encode("admin"), "name", "surname", "mail", List.of(Role.ROLE_USER, Role.ROLE_ADMIN));
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(admin);
    }
}

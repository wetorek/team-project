package com.wetorek.teamproject.repository;

import com.wetorek.teamproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUsername(String username);

    boolean existsByUsernameOrEmail(String username, String email);
}

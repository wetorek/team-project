package com.wetorek.teamproject.auth;

import com.wetorek.teamproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User: " + username + " not found"));
        return new AuthenticatedUser(user.getId(), user.getUsername(), user.getPassword(), user.getRoles());
    }
}

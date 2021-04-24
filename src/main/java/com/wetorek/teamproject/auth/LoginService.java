package com.wetorek.teamproject.auth;

import com.wetorek.teamproject.entity.User;
import com.wetorek.teamproject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserService userService;


    public AuthenticationResponseDto login(AuthenticationRequestDto requestDto) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.getUsername(),
                        requestDto.getPassword()
                )
        );
        var user = (AuthenticatedUser) auth.getPrincipal();
        var roles = userService.findUserByUsername(user.getUsername())
                .map(User::getRoles)
                .orElseThrow(() -> new UsernameNotFoundException("User: " + user.getUsername() + " not found"));
        var token = tokenService.generateNewToken(user, roles);
        return AuthenticationResponseDto.builder()
                .token(token)
                .userId(user.getUserId())
                .build();
    }
}

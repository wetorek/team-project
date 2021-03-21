package com.wetorek.teamproject.auth;

import com.wetorek.teamproject.entity.User;
import com.wetorek.teamproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authenticate")
@AllArgsConstructor
class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    @PostMapping
    ResponseEntity<AuthenticationResponseDto> createToken(@RequestBody AuthenticationRequestDto authRequest) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );
        var user = (UserDetails) auth.getPrincipal();
        var roles = userRepository.findUserByUsername(user.getUsername()).map(User::getRoles).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return ResponseEntity.ok(new AuthenticationResponseDto(tokenService.generateNewToken(user, roles)));
    }
}

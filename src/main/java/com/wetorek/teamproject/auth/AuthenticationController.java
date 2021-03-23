package com.wetorek.teamproject.auth;

import com.wetorek.teamproject.dto.UserRegisterDto;
import com.wetorek.teamproject.entity.User;
import com.wetorek.teamproject.service.RegisterService;
import com.wetorek.teamproject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/authenticate")
@AllArgsConstructor
class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserService userService;
    private final RegisterService registerService;

    @PostMapping
    ResponseEntity<AuthenticationResponseDto> login(@Valid @RequestBody AuthenticationRequestDto authRequest) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );
        var user = (AuthenticatedUser) auth.getPrincipal();
        var roles = userService.findUserByUsername(user.getUsername())
                .map(User::getRoles)
                .orElseThrow(() -> new UsernameNotFoundException("User: " + user.getUsername() + " not found"));
        return ResponseEntity.ok(new AuthenticationResponseDto(tokenService.generateNewToken(user, roles), user.getUserId()));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    void register(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        registerService.register(userRegisterDto);
    }
}

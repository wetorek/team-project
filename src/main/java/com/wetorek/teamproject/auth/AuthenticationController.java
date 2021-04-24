package com.wetorek.teamproject.auth;

import com.wetorek.teamproject.dto.UserRegisterDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/authenticate")
@AllArgsConstructor
class AuthenticationController {
    private final LoginService loginService;
    private final RegisterService registerService;

    @PostMapping
    AuthenticationResponseDto login(@Valid @RequestBody AuthenticationRequestDto authRequest) {
        return loginService.login(authRequest);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    void register(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        registerService.register(userRegisterDto);
    }
}

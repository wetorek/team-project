package com.wetorek.teamproject.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class AuthenticationResponseDto {
    private String token;
}

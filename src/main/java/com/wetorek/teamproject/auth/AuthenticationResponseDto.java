package com.wetorek.teamproject.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthenticationResponseDto {
    private String token;
    private int userId;
}

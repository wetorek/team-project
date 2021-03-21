package com.wetorek.teamproject.auth;

import lombok.Data;

@Data
class AuthenticationRequestDto {
    private String username;
    private String password;
}

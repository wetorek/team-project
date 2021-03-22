package com.wetorek.teamproject.dto;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
}
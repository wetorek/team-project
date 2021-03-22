package com.wetorek.teamproject.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UserRegisterDto {
    @NotEmpty(message = "Username is mandatory")
    private String username;
    @NotEmpty(message = "Password is mandatory")
    private String password;
    @NotEmpty(message = "Name is mandatory")
    private String name;
    @NotEmpty(message = "Surname is mandatory")
    private String surname;
    @NotEmpty(message = "Email is mandatory")
    @Email
    private String email;
}

package com.alkemy.disneyapi.auth.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserDTO {
    @Email(message = "Username must be an email")
    @NotBlank
    private String username;
    @Size(min = 8)
    @NotBlank
    private String password;
}

package com.example.edutrackbackend.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Login Request")
public class AuthRequest {

    @Schema(description = "User email address",  example = "admin@gmail.com")
    private String email;

    @Schema(description = "User password", example = "admin123")
    private String password;
}

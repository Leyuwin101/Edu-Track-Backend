package com.example.edutrackbackend.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Refresh request")
public class RefreshRequest {

    @Schema(description = "JWT refresh token", example = "d9f1f2a1-8b5c-4c7f-a2e3-123456789abc")
    private String refreshToken;
}

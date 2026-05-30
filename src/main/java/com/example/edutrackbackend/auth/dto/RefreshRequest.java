package com.example.edutrackbackend.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Refresh request")
public class RefreshRequest {

    private String refreshToken;
}

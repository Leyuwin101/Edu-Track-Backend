package com.example.edutrackbackend.auth.controller;

import com.example.edutrackbackend.auth.dto.AuthRequest;
import com.example.edutrackbackend.auth.dto.AuthResponse;
import com.example.edutrackbackend.auth.dto.RefreshRequest;
import com.example.edutrackbackend.auth.service.AuthService;
import com.example.edutrackbackend.common.response.ApiRes;
import com.example.edutrackbackend.common.response.ResponseFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication", description = "Authentication Management APIs")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * Login user
     * @param request user credentials (email, password)
     * @return access and refresh token
     */
    @Operation(summary = "Login user and return access + refresh token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "401", description = "Invalid email or password"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PostMapping("/login")
    public ResponseEntity<ApiRes<AuthResponse>> login(@RequestBody AuthRequest request) {

        AuthResponse response = authService.login(request);

        return ResponseFactory.success("Login successfully", response);
    }

    /**
     * Refresh token
     * @param request refresh token
     * @return new token
     */
    @Operation(summary = "Refresh access token using refresh token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token refreshed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "401", description = "Invalid or expired refresh token")
    })
    @PostMapping("/refresh")
    public ResponseEntity<ApiRes<AuthResponse>> refresh(@RequestBody RefreshRequest request) {

        AuthResponse response = authService.refreshAccessToken(request.getRefreshToken());

        return ResponseFactory.success("Refresh token successfully", response);
    }
}

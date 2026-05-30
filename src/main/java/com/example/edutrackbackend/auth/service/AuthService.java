package com.example.edutrackbackend.auth.service;

import com.example.edutrackbackend.auth.dto.AuthRequest;
import com.example.edutrackbackend.auth.dto.AuthResponse;
import com.example.edutrackbackend.auth.exception.AuthException;
import com.example.edutrackbackend.auth.jwt.JwtUtil;
import com.example.edutrackbackend.auth.model.RefreshToken;
import com.example.edutrackbackend.auth.repository.RefreshTokenRepository;
import com.example.edutrackbackend.auth.validator.AuthValidator;
import com.example.edutrackbackend.user.model.User;
import com.example.edutrackbackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenService refreshTokenService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil util;
    private final AuthValidator authValidator;

    /**
     * Authenticates a user using email and password
     *
     * On successful authentication:
     * - generates a JWT access token
     * - creates a refresh token for session persistence
     * - returns both tokens to the client
     *
     * @param request login credentials (email, password)
     * @return authentication response containing access and refresh tokens
     */
    public AuthResponse login(AuthRequest request) {

        log.info("[AUTH][LOGIN] Authenticating email={}", request.getEmail());

        // Validate login request
        authValidator.validateLoginRequest(request);

        // Validate if email exists
        User user = authValidator.validateEmailExists(request.getEmail().trim());

        // Validate if the password matches
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {

            throw new AuthException("Invalid Credentials");
        }

        // Validate the user role
        authValidator.validateUserRole(user);

        // Generate access token
        String accessToken = util.generateToken(user.getEmail(), user.getUserId(), user.getUsername(), user.getRole().name());

        // Generate refresh token
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user, "WEB");

        AuthResponse response = new AuthResponse();
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken.getToken());

        log.info("[AUTH][LOGIN] Authenticating success email={}", request.getEmail());

        return response;

    }

    /**
     * Refreshes authentication tokens using a valid refresh token.
     *
     * Validates the provided refresh token, revokes it to prevent reuse,
     * generates a new access token, and issues a new refresh token.
     *
     * @param refreshToken the refresh token issued during a previous authentication
     * @return a new access token and refresh token pair
     */
    public AuthResponse refreshAccessToken(String refreshToken) {

        log.info("[AUTH][REFRESH] Refresh access token");

        RefreshToken storedToken = refreshTokenService.validate(refreshToken);

        User user = storedToken.getUser();

        // Revoked old refresh token7
        storedToken.setRevoked(true);
        refreshTokenRepository.save(storedToken);

        // Create new Access token
        String newAccessToken = util.generateToken(user.getEmail(), user.getUserId(), user.getUsername(), user.getRole().name());

        // Create new refresh token
        RefreshToken newRefreshToken = refreshTokenService.createRefreshToken(user, "WEB");

        AuthResponse response = new AuthResponse();
        response.setAccessToken(newAccessToken);
        response.setRefreshToken(newRefreshToken.getToken());

        return response;
    }

}

package com.example.edutrackbackend.auth.validator;

import com.example.edutrackbackend.auth.exception.RefreshTokenException;
import com.example.edutrackbackend.auth.model.RefreshToken;
import com.example.edutrackbackend.auth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class RefreshTokenValidator {

    private final RefreshTokenRepository refreshTokenRepository;

    // Validate token
    public RefreshToken validateToken(String token) {

        if (token == null || token.isBlank()) throw new RefreshTokenException("Refresh token missing");

        RefreshToken storedToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RefreshTokenException("Invalid refresh token"));

        // Check if the stored token is expired
        if (storedToken.getExpiryDate().isBefore(LocalDateTime.now())) throw new RefreshTokenException("Refresh token is expired");

        // Check if the stored token is revoked
        if (storedToken.isRevoked()) throw new RefreshTokenException("Refresh token is revoked");

        return storedToken;

    }
}

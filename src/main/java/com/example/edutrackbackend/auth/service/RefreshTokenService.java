package com.example.edutrackbackend.auth.service;

import com.example.edutrackbackend.auth.model.RefreshToken;
import com.example.edutrackbackend.auth.repository.RefreshTokenRepository;
import com.example.edutrackbackend.auth.validator.RefreshTokenValidator;
import com.example.edutrackbackend.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenValidator refreshTokenValidator;

    /**
     * Create refresh token
     *
     * Each token is:
     * - uniquely generated (UUID)
     * - tied to a specific user and device
     * - valid for 7 days
     * - initially not revoked
     *
     * @param user authenticated user
     * @param deviceInfo client device info
     * @return persisted refresh token entity
     */
    public RefreshToken createRefreshToken(User user, String deviceInfo) {

        log.info("[REFRESH_TOKEN][CREATE] Start userId={}", user.getUserId());

        RefreshToken token = new RefreshToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(LocalDateTime.now().plusDays(7));
        token.setDeviceInfo(deviceInfo);
        token.setRevoked(false);

        RefreshToken saved = refreshTokenRepository.save(token);

        log.info("[REFRESH_TOKEN][CREATE] Success userId={}", saved.getUser().getUserId());

        return saved;
    }

    /**
     * Validate refresh token
     *
     * Process:
     * - Use refresh token validator
     * @param token refresh token string
     * @return
     */
    public RefreshToken validate(String token) {

        log.info("[REFRESH-TOKEN][VALIDATE] validating refresh token");

        return refreshTokenValidator.validateToken(token);
    }

}

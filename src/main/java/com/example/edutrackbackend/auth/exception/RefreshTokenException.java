package com.example.edutrackbackend.auth.exception;

import com.example.edutrackbackend.common.enums.ErrorCode;
import com.example.edutrackbackend.common.exception.BaseException;
import org.springframework.http.HttpStatus;

public class RefreshTokenException extends BaseException     {
    public RefreshTokenException(String message) {
        super(
                HttpStatus.UNAUTHORIZED,
                ErrorCode.REFRESH_TOKEN_INVALID,
                "Invalid refresh token",
                message
        );
    }
}

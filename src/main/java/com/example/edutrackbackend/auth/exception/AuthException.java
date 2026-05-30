package com.example.edutrackbackend.auth.exception;

import com.example.edutrackbackend.common.enums.ErrorCode;
import com.example.edutrackbackend.common.exception.BaseException;
import org.springframework.http.HttpStatus;

public class AuthException extends BaseException {
    public AuthException(String message) {
        super(
                HttpStatus.UNAUTHORIZED,
                ErrorCode.AUTH_FAILED,
                "Authentication failed",
                message
        );
    }
}

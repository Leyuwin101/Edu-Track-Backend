package com.example.edutrackbackend.common.exception;

import com.example.edutrackbackend.common.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class EmailAlreadyExistsException extends BaseException {
    public EmailAlreadyExistsException(String message) {
        super(
                HttpStatus.CONFLICT,
                ErrorCode.EMAIL_ALREADY_EXISTS,
                "Email already exists",
                message
        );
    }
}

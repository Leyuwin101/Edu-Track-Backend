package com.example.edutrackbackend.common.exception;

import com.example.edutrackbackend.common.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {

    private final HttpStatus status;
    private final ErrorCode errorCode;
    private final String title;

    public BaseException(
            HttpStatus status,
            ErrorCode errorCode,
            String title,
            String message) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
        this.title = title;
    }
}

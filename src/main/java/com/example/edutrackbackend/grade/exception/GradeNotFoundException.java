package com.example.edutrackbackend.grade.exception;

import com.example.edutrackbackend.common.enums.ErrorCode;
import com.example.edutrackbackend.common.exception.BaseException;
import org.springframework.http.HttpStatus;

public class GradeNotFoundException extends BaseException {
    public GradeNotFoundException(String message) {
        super(
                HttpStatus.NOT_FOUND,
                ErrorCode.GRADE_NOT_FOUND,
                "Grade not found",
                message
        );
    }
}

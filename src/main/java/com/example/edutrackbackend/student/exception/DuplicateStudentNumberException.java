package com.example.edutrackbackend.student.exception;

import com.example.edutrackbackend.common.enums.ErrorCode;
import com.example.edutrackbackend.common.exception.BaseException;
import org.springframework.http.HttpStatus;

public class DuplicateStudentNumberException extends BaseException {
    public DuplicateStudentNumberException(String message) {
        super(
                HttpStatus.CONFLICT,
                ErrorCode.DUPLICATE_STUDENT_NUMBER,
                "Student number already existed",
                message
        );
    }
}

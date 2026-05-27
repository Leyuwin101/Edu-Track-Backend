package com.example.edutrackbackend.student.exception;

import com.example.edutrackbackend.common.enums.ErrorCode;
import com.example.edutrackbackend.common.exception.BaseException;
import org.springframework.http.HttpStatus;

public class StudentNotFoundException extends BaseException {

    public StudentNotFoundException(String message) {
        super(
                HttpStatus.NOT_FOUND,
                ErrorCode.STUDENT_NOT_FOUND,
                "Student not found",
                message
        );
    }
}

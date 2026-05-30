package com.example.edutrackbackend.teacher.exception;

import com.example.edutrackbackend.common.enums.ErrorCode;
import com.example.edutrackbackend.common.exception.BaseException;
import org.springframework.http.HttpStatus;

public class TeacherNotFoundException extends BaseException {
    public TeacherNotFoundException(String message) {
        super(
                HttpStatus.NOT_FOUND,
                ErrorCode.TEACHER_NOT_FOUND,
                "Teacher not found",
                message
        );
    }
}

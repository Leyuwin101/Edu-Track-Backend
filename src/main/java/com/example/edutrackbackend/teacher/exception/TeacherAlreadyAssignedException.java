package com.example.edutrackbackend.teacher.exception;

import com.example.edutrackbackend.common.enums.ErrorCode;
import com.example.edutrackbackend.common.exception.BaseException;
import org.springframework.http.HttpStatus;

public class TeacherAlreadyAssignedException extends BaseException {
    public TeacherAlreadyAssignedException(String message) {
        super(
                HttpStatus.CONFLICT,
                ErrorCode.TEACHER_ALREADY_ASSIGNED,
                "Teacher already assigned",
                message
        );
    }
}

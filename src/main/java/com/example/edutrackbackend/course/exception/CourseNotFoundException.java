package com.example.edutrackbackend.course.exception;

import com.example.edutrackbackend.common.enums.ErrorCode;
import com.example.edutrackbackend.common.exception.BaseException;
import org.springframework.http.HttpStatus;

public class CourseNotFoundException extends BaseException {
    public CourseNotFoundException(String message) {
        super(
                HttpStatus.NOT_FOUND,
                ErrorCode.COURSE_NOT_FOUND,
                "Course not found",
                message
        );
    }
}

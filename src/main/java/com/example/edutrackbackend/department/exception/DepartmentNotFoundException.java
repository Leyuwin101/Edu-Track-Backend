package com.example.edutrackbackend.department.exception;

import com.example.edutrackbackend.common.enums.ErrorCode;
import com.example.edutrackbackend.common.exception.BaseException;
import org.springframework.http.HttpStatus;

public class DepartmentNotFoundException extends BaseException {
    public DepartmentNotFoundException(String message) {
        super(
                HttpStatus.NOT_FOUND,
                ErrorCode.DEPARTMENT_NOT_FOUND,
                "Department not found",
                message
        );
    }
}

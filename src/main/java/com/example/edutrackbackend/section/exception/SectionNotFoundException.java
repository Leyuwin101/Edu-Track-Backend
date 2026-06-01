package com.example.edutrackbackend.section.exception;

import com.example.edutrackbackend.common.enums.ErrorCode;
import com.example.edutrackbackend.common.exception.BaseException;
import org.springframework.http.HttpStatus;

public class SectionNotFoundException extends BaseException {
    public SectionNotFoundException(String message) {
        super(
                HttpStatus.NOT_FOUND,
                ErrorCode.SECTION_NOT_FOUND,
                "Section not found",
                message
        );
    }
}

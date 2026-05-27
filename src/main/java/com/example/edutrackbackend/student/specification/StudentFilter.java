package com.example.edutrackbackend.student.specification;

import com.example.edutrackbackend.student.enums.Gender;
import com.example.edutrackbackend.student.enums.StudentStatus;
import com.example.edutrackbackend.student.enums.YearLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentFilter {

    // Student filter for Student Specification

    private String keyword;

    private YearLevel yearLevel;

    private StudentStatus status;

    private Gender gender;

    private String section;
}

package com.example.edutrackbackend.teacher.specification;

import com.example.edutrackbackend.common.enums.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherFilter {

    // Teacher Filter for Teacher Specification

    private String keyword;

    private String department;

    private Gender gender;

    private String specialization;
}

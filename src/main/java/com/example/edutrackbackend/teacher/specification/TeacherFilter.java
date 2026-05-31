package com.example.edutrackbackend.teacher.specification;

import com.example.edutrackbackend.common.enums.Gender;
import com.example.edutrackbackend.department.model.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherFilter {

    // Teacher Filter for Teacher Specification

    private String keyword;

    private Long departmentId;

    private Gender gender;

    private String specialization;
}

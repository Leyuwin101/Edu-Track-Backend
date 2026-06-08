package com.example.edutrackbackend.grade.dto;

import com.example.edutrackbackend.course.dto.CourseDTO;
import com.example.edutrackbackend.student.dto.StudentDTO;
import com.example.edutrackbackend.teacher.dto.TeacherDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeResponse {

    private Long gradeId;

    private StudentDTO student;

    private CourseDTO course;

    private TeacherDTO teacher;

    private Double scores;

    private String remarks;

    private LocalDateTime createdAt;
}

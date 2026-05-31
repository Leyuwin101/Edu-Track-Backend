package com.example.edutrackbackend.department.dto;

import com.example.edutrackbackend.teacher.dto.TeacherDTO;
import com.example.edutrackbackend.teacher.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponse {

    private Long departmentId;

    private String departmentName;

    private TeacherDTO departmentHead;

    private List<TeacherDTO> teachers;
}

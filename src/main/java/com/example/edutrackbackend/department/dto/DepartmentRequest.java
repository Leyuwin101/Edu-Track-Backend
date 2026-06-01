package com.example.edutrackbackend.department.dto;

import com.example.edutrackbackend.teacher.dto.TeacherDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequest {

    @NotBlank(message = "Department name is required")
    private String departmentName;

    private Long departmentHeadId;

}

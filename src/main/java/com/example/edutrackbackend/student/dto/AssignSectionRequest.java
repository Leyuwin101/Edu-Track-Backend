package com.example.edutrackbackend.student.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignSectionRequest {

    @NotNull(message = "Student id is required")
    private Long studentId;

    @NotBlank(message = "Section is required")
    private String section;
}

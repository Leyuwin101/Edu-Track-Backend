package com.example.edutrackbackend.grade.dto;

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
public class GradeRequest {

    @NotNull(message = "student id is required")
    private Long studentId;

    @NotNull(message = "course id is required")
    private Long courseId;

    @NotNull(message = "teacher id is required")
    private Long teacherId;

    @NotNull(message = "score is required")
    private Double score;

    @NotBlank(message = "remarks is required")
    private String remarks;
}

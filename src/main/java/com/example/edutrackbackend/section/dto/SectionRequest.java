package com.example.edutrackbackend.section.dto;

import com.example.edutrackbackend.course.dto.CourseDTO;
import com.example.edutrackbackend.student.enums.YearLevel;
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
public class SectionRequest {

    @NotBlank(message = "Section code is required")
    private String sectionCode;

    @NotBlank(message = "Section name is required")
    private String sectionName;

    @NotNull(message = "Year level is required")
    private YearLevel yearLevel;

    @NotNull(message = "Course is required")
    private Long courseId;
}

package com.example.edutrackbackend.student.dto;

import com.example.edutrackbackend.section.model.Section;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Assign section to student request")
public class AssignSectionRequest {

    @NotNull(message = "Student id is required")
    private Long studentId;

    @NotBlank(message = "Section id is required")
    private Long sectionId;
}

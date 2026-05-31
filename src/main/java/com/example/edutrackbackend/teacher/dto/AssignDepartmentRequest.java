package com.example.edutrackbackend.teacher.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Assign department to teacher request")
public class AssignDepartmentRequest {
    
    @NotNull
    private Long teacherId;

    @NotNull
    private Long departmentId;

}

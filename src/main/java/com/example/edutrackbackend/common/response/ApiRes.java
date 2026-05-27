package com.example.edutrackbackend.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
@Schema(description = "Standard API response wrapper")
public class ApiRes<T> {

    @Schema(description = "Response status (success/error)")
    private String status;

    @Schema(description = "Message Describing the result")
    private String name;

    @Schema(description = "Actual Response Data")
    private T data;
}

package com.example.edutrackbackend.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
@Builder
@AllArgsConstructor
@Schema(description = "Paginated Response Wrapper")
public class PaginatedRes<T> {

    @Schema(description = "Response status (success/error)")
    private String status;

    @Schema(description = "List of data items")
    private List<T> data;

    @Schema(description = "Current page number")
    private int currentPage;

    @Schema(description = "Total number of pages")
    private int totalPage;

    @Schema(description = "Total items")
    private long totalItems;


}

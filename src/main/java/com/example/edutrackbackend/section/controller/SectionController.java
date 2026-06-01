package com.example.edutrackbackend.section.controller;

import com.example.edutrackbackend.common.response.ApiRes;
import com.example.edutrackbackend.common.response.ResponseFactory;
import com.example.edutrackbackend.section.dto.SectionRequest;
import com.example.edutrackbackend.section.dto.SectionResponse;
import com.example.edutrackbackend.section.service.SectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Sections", description = "Section Management APIs")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sections")
public class SectionController {

    private final SectionService sectionService;

    /**
     * Create new Section
     *
     * Accessible by ADMIN and REGISTRAR only
     *
     * @param request section registration data
     * @return created section response
     */
    @Operation(summary = "Create section", description = "Accessibly by ADMIN and REGISTRAR only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Section created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', REGISTRAR')")
    public ResponseEntity<ApiRes<SectionResponse>> createSection(@Valid @RequestBody SectionRequest request) {

        SectionResponse section = sectionService.createSection(request);

        return ResponseFactory.created("Section created successfully", section);
    }

    /**
     * Update existing section
     *
     * Accessible by ADMIN and REGISTRAR only
     *
     * @param sectionId section id to update
     * @param request updated section data
     * @return updated section response
     */
    @Operation(summary = "Update Section", description = "Accessible by ADMIN and REGISTRAR only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Section updated successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "404", description = "Section not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','REGISTRAR')")
    public ResponseEntity<ApiRes<SectionResponse>> updateSection(@PathVariable("id") Long sectionId, @Valid @RequestBody SectionRequest request) {

        SectionResponse updated = sectionService.updateSection(sectionId, request);

        return ResponseFactory.success("Section updated successfully", updated);
    }


    /**
     * Delete existing section
     *
     * Accessible by ADMIN and REGISTRAR only
     *
     * @param sectionId section id to delete
     * @return no content
     */
    @Operation(summary = "Delete Section", description = "Accessible by ADMIN and REGISTRAR only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Section deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Section not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','REGISTRAR')")
    public ResponseEntity<ApiRes<Void>> deleteSection(@PathVariable("id") Long sectionId) {

        sectionService.deleteSection(sectionId);

        return ResponseEntity.noContent().build();
    }


    /**
     * Fetch section by id
     *
     * Accessible by ADMIN and REGISTRAR only
     *
     * @param sectionId section id to fetch
     * @return fetched section response
     */
    @Operation(summary = "Get Section By Id", description = "Accessible by ADMIN and REGISTRAR only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Section fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Section not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','REGISTRAR')")
    public ResponseEntity<ApiRes<SectionResponse>> getSectionById(@PathVariable("id") Long sectionId) {

        SectionResponse section = sectionService.getSectionById(sectionId);

        return ResponseFactory.success("Section fetched successfully", section);
    }

    /**
     * Fetch all sections
     *
     * Accessible by ADMIN and REGISTRAR only
     *
     * @return List of Sections
     */
    @Operation(summary = "Get all Sections", description = "Accessible by ADMIN and REGISTRAR only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Sections Fetched successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','REGISTRAR')")
    public ResponseEntity<ApiRes<List<SectionResponse>>> getAllSections() {

        List<SectionResponse> sections = sectionService.getAllSections();

        return ResponseFactory.success("All sections fetched successfully", sections);
    }
}

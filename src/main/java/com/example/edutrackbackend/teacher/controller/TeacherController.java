package com.example.edutrackbackend.teacher.controller;

import com.example.edutrackbackend.common.response.ApiRes;
import com.example.edutrackbackend.common.response.PaginatedRes;
import com.example.edutrackbackend.common.response.ResponseFactory;
import com.example.edutrackbackend.teacher.dto.AssignDepartmentRequest;
import com.example.edutrackbackend.teacher.dto.TeacherRequest;
import com.example.edutrackbackend.teacher.dto.TeacherResponse;
import com.example.edutrackbackend.teacher.service.TeacherService;
import com.example.edutrackbackend.teacher.specification.TeacherFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Teachers", description = "Teacher Management APIs")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    /**
     * Create new Teacher
     *
     * Accessible by ADMIN only
     *
     * @param request teacher registration data
     * @return created teacher response
     */
    @Operation(summary = "Create teacher", description = "Accessible by ADMIN only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Teacher created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiRes<TeacherResponse>> createTeacher(@Valid @RequestBody TeacherRequest request) {

        TeacherResponse teacher = teacherService.createTeacher(request);

        return ResponseFactory.created("Teacher created successfully", teacher);
    }

    /**
     * Update existing teacher
     *
     * Accessible by ADMIN only
     *
     * @param teacherId teacher id to update
     * @param request updated teacher data
     * @return updated teacher response
     */
    @Operation(summary = "Update Teacher", description = "Accessible by ADMIN only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher updated successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "404", description = "Teacher not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIM')")
    public ResponseEntity<ApiRes<TeacherResponse>> updateTeacher(@PathVariable("id") Long teacherId, @Valid @RequestBody TeacherRequest request) {

        TeacherResponse updated = teacherService.updateTeacher(teacherId, request);

        return ResponseFactory.success("Teacher updated successfully", updated);
    }


    /**
     * Delete existing teacher
     *
     * Accessible by ADMIN only
     *
     * @param teacherId teacher id to delete
     * @return no content
     */
    @Operation(summary = "Delete Teacher", description = "Accessible by ADMIN only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Teacher deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Teacher not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiRes<Void>> deleteTeacher(@PathVariable("id") Long teacherId) {

        teacherService.deleteTeacher(teacherId);

        return ResponseEntity.noContent().build();
    }

    /**
     * Fetch teacher by id
     *
     * Accessible by ADMIN only
     *
     * @param teacherId teacher id to fetch
     * @return fetched teacher response
     */
    @Operation(summary = "Get Teacher By Id", description = "Accessible by ADMIN only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Teacher not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiRes<TeacherResponse>> getTeacherById(@PathVariable("id") Long teacherId) {

        TeacherResponse teacher = teacherService.getTeacherById(teacherId);

        return ResponseFactory.success("Teacher fetched successfully", teacher);
    }


    /**
     * Fetch all teachers
     *
     * Accessible by ADMIN only
     *
     * @return List of Teachers
     */
    @Operation(summary = "Get all Teachers", description = "Accessible by ADMIN only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Teachers Fetched successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiRes<List<TeacherResponse>>> getAllTeachers() {

        List<TeacherResponse> teachers = teacherService.getAllTeachers();

        return ResponseFactory.success("All Teachers fetched successfully", teachers);
    }



    /**
     * Search teacher
     *
     * Accessible by ADMIN only
     *
     * @param filter filtered keywords
     * @param pageable pagination information
     * @return paginated list of searched teachers
     */
    @Operation(summary = "Search Teacher", description = "Accessible by ADMIN only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Searched Teachers Successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "400", description = "Invalid search parameters"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiRes<PaginatedRes<TeacherResponse>>> searchTeacher(@ModelAttribute TeacherFilter filter, Pageable pageable) {

        PaginatedRes<TeacherResponse> teachers = teacherService.searchTeacher(filter, pageable);

        return ResponseFactory.success("Searched Teachers Successfully", teachers);
    }

    /**
     * Assign teacher to department
     *
     * Accessibly by ADMIN and REGISTRAR only
     *
     * @param request assigning department data
     * @return teacher response
     */
    @Operation(summary = "Assign teacher to department", description = "Accessibly by ADMIN and REGISTRAR ONLY")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Teacher department assigned successfully"),
            @ApiResponse(responseCode = "404", description = "Teacher/Department not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403",description = "Access denied")
    })
    @PostMapping("/assign")
    @PreAuthorize("hasAnyRole('ADMIN','REGISTRAR')")
    public ResponseEntity<ApiRes<TeacherResponse>> assignDepartment(@Valid @RequestBody AssignDepartmentRequest request) {

        TeacherResponse assign = teacherService.assignDepartment(request);

        return ResponseFactory.success("Teacher assigned to department successfully", assign);
    }


}

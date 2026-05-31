package com.example.edutrackbackend.department.controller;

import com.example.edutrackbackend.common.response.ApiRes;
import com.example.edutrackbackend.common.response.ResponseFactory;
import com.example.edutrackbackend.department.dto.DepartmentRequest;
import com.example.edutrackbackend.department.dto.DepartmentResponse;
import com.example.edutrackbackend.department.service.DepartmentService;
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

@Tag(name = "Departments", description = "Department Management APIS")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    /**
     * Create deparment
     *
     * Accessible by ADMIN only
     *
     * @param request department registration data
     * @return created department response
     */
    @Operation(summary = "Create department", description = "Accessible by ADMIN only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Department created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "404", description = "Teacher not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiRes<DepartmentResponse>> createDepartment(@Valid @RequestBody DepartmentRequest request) {

        DepartmentResponse department = departmentService.createDepartment(request);

        return ResponseFactory.created("Department created successfully", department);
    }

    /**
     * Update existing department
     *
     * Accessible by ADMIN only
     *
     * @param departmentId department id to update
     * @param request updated department data
     * @return update department response
     */
    @Operation(summary = "Update department", description = "Accessible by ADMIN only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Department updated successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "404", description = "Department not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiRes<DepartmentResponse>> updateDepartment(@PathVariable("id") Long departmentId, @Valid @RequestBody DepartmentRequest request) {

        DepartmentResponse updated = departmentService.updateDepartment(departmentId, request);

        return ResponseFactory.success("Department updated successfully", updated);
    }


    /**
     * Delete existing department
     *
     * Accessible by ADMIN only
     *
     * @param departmentId department id to delete
     * @return no content
     */
    @Operation(summary = "Delete department", description = "Accessible by ADMIN only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Department deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Department not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiRes<Void>> deleteDepartment(@PathVariable("id") Long departmentId) {

        departmentService.deleteDepartment(departmentId);

        return ResponseEntity.noContent().build();
    }

    /**
     * Fetched department by id
     *
     * Accessible by ADMIN only
     *
     * @param departmentId department id to fetch
     * @return fetched department response
     */
    @Operation(summary = "Get department by id", description = "Accessible by ADMIN only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Department fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Department not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiRes<DepartmentResponse>> getDepartmentById(@PathVariable("id") Long departmentId) {

        DepartmentResponse department = departmentService.getDepartmentById(departmentId);

        return ResponseFactory.success("Department fetched successfully", department);
    }

    /**
     * Fetch all department
     *
     * Accessible by ADMIN only
     *
     * @return List of departments
     */
    @Operation(summary = "Get all department", description = "Accessible by ADMIN only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Departments fetched successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiRes<List<DepartmentResponse>>> getAllDepartments() {

        List<DepartmentResponse> departments = departmentService.getAllDepartment();

        return ResponseFactory.success("All departments fetched successfully", departments);
    }




}

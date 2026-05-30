package com.example.edutrackbackend.student.controller;

import com.example.edutrackbackend.common.response.ApiRes;
import com.example.edutrackbackend.common.response.PaginatedRes;
import com.example.edutrackbackend.common.response.ResponseFactory;
import com.example.edutrackbackend.student.dto.AssignSectionRequest;
import com.example.edutrackbackend.student.dto.StudentRequest;
import com.example.edutrackbackend.student.dto.StudentResponse;
import com.example.edutrackbackend.student.enums.StudentStatus;
import com.example.edutrackbackend.student.service.StudentService;
import com.example.edutrackbackend.student.specification.StudentFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Tag(name = "Students", description = "Student Management APIs")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    /**
     * Create new Student
     *
     * Accessible by ADMIN only
     *
     * @param request student registration data
     * @return created student response
     */
    @Operation(summary = "Create new student", description = "Accessible by ADMIN only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiRes<StudentResponse>> createStudent(@Valid @RequestBody StudentRequest request) {

        StudentResponse student = studentService.createStudent(request);

        return ResponseFactory.created("Student created successfully", student);
    }

    /**
     * Updates an existing user
     *
     * Accessible by ADMIN only
     *
     * @param studentId student id to update
     * @param request updated student registration data
     * @return updated student response
     */
    @Operation(summary = "Update existing student", description = "Accessible by ADMIN only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiRes<StudentResponse>> updateStudent(@PathVariable("id") Long studentId, @Valid @RequestBody StudentRequest request) {

        StudentResponse student = studentService.updateStudent(studentId, request);

        return ResponseFactory.success("Student updated successfully", student);
    }

    /**
     * Delete existing student
     *
     * Accessible by ADMIN only
     * @param studentId student id to delete
     * @return no content
     */
    @Operation(summary = "Delete existing student", description = "Accessible by ADMIN only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiRes<Void>> deleteStudent(@PathVariable("id") Long studentId) {

        studentService.deleteStudent(studentId);

        return ResponseEntity.noContent().build();
    }


    /**
     * Fetch student by id
     *
     * Accessible by ADMIN only
     *
     * @param studentId student id to fetch
     * @return student response
     */
    @Operation(summary = "Get student by id", description = "Accessible by ADMIN only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiRes<StudentResponse>> getStudentById(@PathVariable("id") Long studentId) {

        StudentResponse student = studentService.getStudentById(studentId);

        return ResponseFactory.success("Student fetched successfully", student);
    }

    /**
     * Fetch all students
     *
     * Accessible by ADMIN only
     *
     * @return list of students
     */
    @Operation(summary = "Get all students", description = "Accessible by ADMIN only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All students fetched successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiRes<List<StudentResponse>>> getAllStudents() {

        List<StudentResponse> students = studentService.getAllStudents();

        return ResponseFactory.success("All Students fetched successfully", students);
    }

    /**
     * Search student
     *
     * Accessible by ADMIN and Teacher only
     *
     * @param filter filtered keywords
     * @param pageable pagination information
     * @return paginated list of searched students
     */
    @Operation(summary = "Search students", description = "Accessible by ADMIN and TEACHER only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Searched Student(s) fetch successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid search parameters"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<ApiRes<PaginatedRes<StudentResponse>>> searchStudent(@ModelAttribute StudentFilter filter,  Pageable pageable) {

        PaginatedRes<StudentResponse> response = studentService.searchStudents(filter, pageable);

        return ResponseFactory.success("Searched Student(s) fetch successfully", response);

    }

    /**
     * Assign section to student
     *
     * Accessible by ADMIN only
     *
     * @param request assign section request data
     * @return student response
     */
    @Operation(summary = "Assign section", description = "Accessible by ADMIN only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student section assigned successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403",description = "Access denied")
    })
    @PostMapping("/assign/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiRes<StudentResponse>> assignSection(@RequestBody AssignSectionRequest request) {

        StudentResponse student = studentService.assignSection(request.getStudentId(), request.getSection());

        return ResponseFactory.success("Student section assigned successfully", student);
    }

    /**
     * Update student status
     *
     * Accessible to ADMIN and TEACHER only
     *
     * @param studentId student id to update
     * @param status updated status
     * @return update student status response
     */
    @Operation(summary = "Update status", description = "Accessible by ADMIN and TEACHER only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student status updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid status value"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403",description = "Access denied")
    })
    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public ResponseEntity<ApiRes<StudentResponse>> updateStatus(@PathVariable("id") Long studentId, @RequestParam StudentStatus status) {

        StudentResponse student = studentService.updateStatus(studentId, status);

        return ResponseFactory.success("Student status updated successfully", student);
    }





}

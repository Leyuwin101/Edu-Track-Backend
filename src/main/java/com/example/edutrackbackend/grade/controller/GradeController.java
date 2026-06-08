package com.example.edutrackbackend.grade.controller;

import com.example.edutrackbackend.common.response.ApiRes;
import com.example.edutrackbackend.common.response.ResponseFactory;
import com.example.edutrackbackend.grade.dto.GradeRequest;
import com.example.edutrackbackend.grade.dto.GradeResponse;
import com.example.edutrackbackend.grade.service.GradeService;
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

@Tag(name = "Grades", description = "Grade Management APIs")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/grades")
public class GradeController {

    private final GradeService gradeService;

    /**
     * Create new Grade
     *
     * Accessible by ADMIN and REGISTRAR only
     *
     * @param request grade registration data
     * @return created grade response
     */
    @Operation(summary = "Create new grade", description = "Accessible by ADMIN and REGISTRAR only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Grade created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "404", description = "Student/Course/Teacher not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','REGISTRAR')")
    public ResponseEntity<ApiRes<GradeResponse>> createGrade(@Valid @RequestBody GradeRequest request) {

        GradeResponse grade = gradeService.createGrade(request);

        return ResponseFactory.created("Grade created successfully", grade);
    }

    /**
     * Updates an existing grade
     *
     * Accessible by ADMIN and REGISTRAR only
     *
     * @param gradeId grade id to update
     * @param request updated grade registration data
     * @return updated grade response
     */
    @Operation(summary = "Update existing grade", description = "Accessible by ADMIN and REGISTRAR only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grade updated successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "404", description = "Grade not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','REGISTRAR')")
    public ResponseEntity<ApiRes<GradeResponse>> updateGrade(@PathVariable("id") Long gradeId, @Valid @RequestBody GradeRequest request) {

        GradeResponse grade = gradeService.updateGrade(gradeId, request);

        return ResponseFactory.success("Grade updated successfully", grade);
    }

    /**
     * Delete existing grade
     *
     * Accessible by ADMIN and REGISTRAR only
     * @param gradeId grade id to delete
     * @return no content
     */
    @Operation(summary = "Delete existing grade", description = "Accessible by ADMIN and REGISTRAR only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Grade deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Grade not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','REGISTRAR')")
    public ResponseEntity<ApiRes<Void>> deleteGrade(@PathVariable("id") Long gradeId) {

        gradeService.deleteGrade(gradeId);

        return ResponseEntity.noContent().build();
    }

    /**
     * Fetch grade by id
     *
     * Accessible by ADMIN and REGISTRAR only
     *
     * @param gradeId grade id to fetch
     * @return grade response
     */
    @Operation(summary = "Get grade by id", description = "Accessible by ADMIN and REGISTRAR only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grade fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Grade not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','REGISTRAR')")
    public ResponseEntity<ApiRes<GradeResponse>> getGradeById(@PathVariable("id") Long gradeId) {

        GradeResponse grade = gradeService.getGradeById(gradeId);

        return ResponseFactory.success("Grade fetched successfully", grade);
    }

    /**
     * Fetch all grades
     *
     * Accessible by ADMIN and REGISTRAR only
     *
     * @return list of grades
     */
    @Operation(summary = "Get all grades", description = "Accessible by ADMIN and REGISTRAR only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All grades fetched successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','REGISTRAR')")
    public ResponseEntity<ApiRes<List<GradeResponse>>> getAllGrades() {

        List<GradeResponse> grades = gradeService.getAllGrades();

        return ResponseFactory.success("All grades fetched successfully", grades);
    }

    /**
     * Fetch all grades by student
     *
     * Accessible by ADMIN and REGISTRAR only
     *
     * @return list of grades of student
     */
    @Operation(summary = "Get all grades of student", description = "Accessible by ADMIN and REGISTRAR only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All grades fetched successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/student/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','REGISTRAR')")
    public ResponseEntity<ApiRes<List<GradeResponse>>> getGradesByStudent(@PathVariable("id") Long studentId) {

        List<GradeResponse> grades = gradeService.getGradeByStudent(studentId);

        return ResponseFactory.success("Grades by student fetched successfully", grades);
    }

    /**
     * Fetch all grades by course
     *
     * Accessible by ADMIN and REGISTRAR only
     *
     * @return list of grades of course
     */
    @Operation(summary = "Get all grades by course", description = "Accessible by ADMIN and REGISTRAR only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All grades fetched successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Course not found"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/course/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','REGISTRAR')")
    public ResponseEntity<ApiRes<List<GradeResponse>>> getGradesByCourse(@PathVariable("id") Long courseId) {

        List<GradeResponse> grades = gradeService.getGradeByCourse(courseId);

        return ResponseFactory.success("Grades by course fetched successfully", grades);
    }

    /**
     * Fetch all grades by teacher
     *
     * Accessible by ADMIN and REGISTRAR only
     *
     * @return list of grades of teacher
     */
    @Operation(summary = "Get all grades of teacher", description = "Accessible by ADMIN and REGISTRAR only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All grades fetched successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Teacher not found"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/teacher/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','REGISTRAR')")
    public ResponseEntity<ApiRes<List<GradeResponse>>> getGradesByTeacher(@PathVariable("id") Long teacherId) {

        List<GradeResponse> grades = gradeService.getGradeByTeacher(teacherId);

        return ResponseFactory.success("Grades by teacher fetched successfully", grades);
    }
}

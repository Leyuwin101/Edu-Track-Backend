package com.example.edutrackbackend.grade.service;

import com.example.edutrackbackend.grade.dto.GradeRequest;
import com.example.edutrackbackend.grade.dto.GradeResponse;

import java.util.List;

public interface GradeService {

    GradeResponse createGrade(GradeRequest request);

    GradeResponse updateGrade(Long gradeId, GradeRequest request);

    void deleteGrade(Long gradeId);

    GradeResponse getGradeById(Long gradeId);

    List<GradeResponse> getAllGrades();

    List<GradeResponse> getGradeByStudent(Long studentId);

    List<GradeResponse> getGradeByCourse(Long courseId);

    List<GradeResponse> getGradeByTeacher(Long teacherId);

    String calculateRemarks(Double score);
}

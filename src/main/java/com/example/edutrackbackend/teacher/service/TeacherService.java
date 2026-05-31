package com.example.edutrackbackend.teacher.service;

import com.example.edutrackbackend.common.response.PaginatedRes;
import com.example.edutrackbackend.teacher.dto.AssignDepartmentRequest;
import com.example.edutrackbackend.teacher.dto.TeacherRequest;
import com.example.edutrackbackend.teacher.dto.TeacherResponse;
import com.example.edutrackbackend.teacher.specification.TeacherFilter;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeacherService {

    TeacherResponse createTeacher(TeacherRequest request);

    TeacherResponse updateTeacher(Long teacherId, TeacherRequest request);

    void deleteTeacher(Long teacherId);

    TeacherResponse getTeacherById(Long teacherId);

    List<TeacherResponse> getAllTeachers();

    PaginatedRes<TeacherResponse> searchTeacher(TeacherFilter filter, Pageable pageable);

    TeacherResponse assignDepartment(AssignDepartmentRequest request);
}

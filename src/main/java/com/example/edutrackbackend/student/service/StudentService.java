package com.example.edutrackbackend.student.service;

import com.example.edutrackbackend.common.response.PaginatedRes;
import com.example.edutrackbackend.student.dto.StudentRequest;
import com.example.edutrackbackend.student.dto.StudentResponse;
import com.example.edutrackbackend.student.enums.StudentStatus;
import com.example.edutrackbackend.student.specification.StudentFilter;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {

    StudentResponse createStudent(StudentRequest request);

    StudentResponse updateStudent(Long studentId, StudentRequest request);

    void deleteStudent(Long studentId);

    StudentResponse getStudentById(Long studentId);

    List<StudentResponse> getAllStudents();

    PaginatedRes<StudentResponse> searchStudents(StudentFilter filter, Pageable pageable);

    StudentResponse assignSection(Long studentId, String section);

    StudentResponse updateStatus(Long studentId, StudentStatus status);

}

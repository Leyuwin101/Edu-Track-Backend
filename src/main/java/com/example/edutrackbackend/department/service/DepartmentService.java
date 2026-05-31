package com.example.edutrackbackend.department.service;

import com.example.edutrackbackend.department.dto.DepartmentRequest;
import com.example.edutrackbackend.department.dto.DepartmentResponse;

import java.util.List;

public interface DepartmentService {

    DepartmentResponse createDepartment(DepartmentRequest request);

    DepartmentResponse updateDepartment(Long departmentId, DepartmentRequest request);

    void deleteDepartment(Long departmentId);

    DepartmentResponse getDepartmentById(Long departmentId);

    List<DepartmentResponse> getAllDepartment();
}

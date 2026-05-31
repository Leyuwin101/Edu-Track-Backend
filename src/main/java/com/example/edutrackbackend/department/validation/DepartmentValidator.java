package com.example.edutrackbackend.department.validation;

import com.example.edutrackbackend.department.exception.DepartmentNotFoundException;
import com.example.edutrackbackend.department.model.Department;
import com.example.edutrackbackend.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartmentValidator {

    private final DepartmentRepository departmentRepository;

    // Department exist validator
    public Department hasDepartmentExists(Long departmentId) {

        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found: " + departmentId));

    }
}

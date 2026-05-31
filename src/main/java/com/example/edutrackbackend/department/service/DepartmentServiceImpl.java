package com.example.edutrackbackend.department.service;

import com.example.edutrackbackend.department.dto.DepartmentRequest;
import com.example.edutrackbackend.department.dto.DepartmentResponse;
import com.example.edutrackbackend.department.mapper.DepartmentMapper;
import com.example.edutrackbackend.department.model.Department;
import com.example.edutrackbackend.department.repository.DepartmentRepository;
import com.example.edutrackbackend.department.validation.DepartmentValidator;
import com.example.edutrackbackend.teacher.model.Teacher;
import com.example.edutrackbackend.teacher.validation.TeacherValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentValidator departmentValidator;
    private final DepartmentMapper departmentMapper;
    private final TeacherValidator teacherValidator;

    /**
     * Create department
     *
     * Process:
     * - Check if the teacher exist first for the department head
     * - Map the request dto to Departmen Entity(db)
     * - Saved the department to the database
     *
     * @param request Department registration data
     * @return department response
     */
    @Override
    public DepartmentResponse createDepartment(DepartmentRequest request) {

        log.info("[DEPARTMENT][CREATE] Start departmentName={}", request.getDepartmentName());

        Teacher teacher = teacherValidator.validateTeacherExists(request.getDepartmentHead().getTeacherId());

        Department department = departmentMapper.toEntity(request, teacher);

        Department saved = departmentRepository.save(department);

        log.info("[DEPARTMENT][CREATE] Success departmentName={}", saved.getDepartmentName());

        return departmentMapper.toDto(saved);

    }

    /**
     * Update existing department
     *
     * Process:
     * - Check if the department exist in the database
     * - Map the updated request dto to Department Entity(db)
     * - validator if it has departmentHead in the update
     *
     * @param departmentId department id to update
     * @param request updated department data
     * @return update department response
     */
    @Override
    public DepartmentResponse updateDepartment(Long departmentId, DepartmentRequest request) {

        log.info("[DEPARTMENT][UPDATE] Start departmentId={}", departmentId);

        Department department = departmentValidator.hasDepartmentExists(departmentId);

        departmentMapper.updateEntity(department, request);

        if (request.getDepartmentHead() != null) {
            Teacher teacher = teacherValidator.validateTeacherExists(request.getDepartmentHead().getTeacherId());

            department.setDepartmentHead(teacher);
        }

        Department saved = departmentRepository.save(department);

        log.info("[DEPARTMENT][UPDATE] Success departmentId={}", departmentId);

        return departmentMapper.toDto(saved);

    }

    /**
     * Delete department
     *
     * Process:
     * - Check if the department exists first in the database
     * - Delete it from the database
     *
     * @param departmentId department id to delete
     */
    @Override
    public void deleteDepartment(Long departmentId) {

        log.info("[DEPARTMENT][DELETE] Start departmentId={}", departmentId);

        Department department = departmentValidator.hasDepartmentExists(departmentId);

        departmentRepository.delete(department);

        log.info("[DEPARTMENT][DELETE] Success departmentId={}", departmentId);
    }

    /**
     * Get department by id
     *
     * Process:
     * - Check if the department exists first in the database
     *
     * @param departmentId department id to find
     * @return department response
     */
    @Override
    public DepartmentResponse getDepartmentById(Long departmentId) {

        log.info("[DEPARTMENT][GET] Start departmentId={}", departmentId);

        Department department = departmentValidator.hasDepartmentExists(departmentId);

        log.info("[DEPARTMENT][GET] Success departmentId={}", departmentId);

        return departmentMapper.toDto(department);
    }

    /**
     * Get all department
     *
     * Process:
     * - Find all the department in the database
     *
     * @return List of departments
     */
    @Override
    public List<DepartmentResponse> getAllDepartment() {

        log.info("[DEPARTMENT][GET_ALL] Fetching all department");

        List<Department> departments = departmentRepository.findAll();

        return departments.stream()
                .map(departmentMapper::toDto)
                .toList();
     }
}

package com.example.edutrackbackend.department.mapper;

import com.example.edutrackbackend.department.dto.DepartmentRequest;
import com.example.edutrackbackend.department.dto.DepartmentResponse;
import com.example.edutrackbackend.department.model.Department;
import com.example.edutrackbackend.teacher.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = com.example.edutrackbackend.teacher.mapper.TeacherMapper.class)
public interface DepartmentMapper {

    /**
     * Converts DepartmentRequest + Teacher into Department entity.
     * Used when creating a new Department with an already-resolved department head.
     */
    @Mapping(target = "departmentId", ignore = true)
    @Mapping(target = "departmentHead", source = "teacher")
    @Mapping(target = "teachers", ignore = true)
    Department toEntity(DepartmentRequest request, Teacher teacher);

    /**
     * Converts Department entity into DepartmentResponse DTO.
     * Maps department head into TeacherDTO using TeacherMapper#toTeacherDTO.
     */
    @Mapping(target = "departmentHead", source = "departmentHead", qualifiedByName = "toTeacherDTO")
    DepartmentResponse toDto(Department department);

    /**
     * Updates an existing Department entity with values from DepartmentRequest.
     * Ignores system-managed fields and relationships.
     */
    @Mapping(target = "departmentId", ignore = true)
    @Mapping(target = "departmentHead", ignore = true)
    @Mapping(target = "teachers", ignore = true)
    void updateEntity(@MappingTarget Department department, DepartmentRequest request);

}
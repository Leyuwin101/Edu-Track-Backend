package com.example.edutrackbackend.department.mapper;

import com.example.edutrackbackend.department.dto.DepartmentDTO;
import com.example.edutrackbackend.department.dto.DepartmentRequest;
import com.example.edutrackbackend.department.dto.DepartmentResponse;
import com.example.edutrackbackend.department.model.Department;
import com.example.edutrackbackend.teacher.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = com.example.edutrackbackend.teacher.mapper.TeacherMapper.class)
public interface DepartmentMapper {

    /**
     * Creates a new Department entity from the request DTO.
     *
     * The department head is supplied separately as a validated Teacher entity
     * retrieved from the database. The teachers collection is ignored because
     * teachers are assigned through their own relationship management process.
     */
    @Mapping(target = "departmentId", ignore = true)
    @Mapping(target = "departmentHead", source = "teacher")
    @Mapping(target = "teachers", ignore = true)
    Department toEntity(DepartmentRequest request, Teacher teacher);

    /**
     * Converts a Department entity into a response DTO.
     *
     * Uses TeacherMapper to convert the department head entity into a
     * TeacherDTO. The teachers list is automatically mapped using the
     * configured TeacherMapper.
     */
    @Mapping(target = "departmentHead", source = "departmentHead", qualifiedByName = "toTeacherDTO")
    DepartmentResponse toDto(Department department);


    /**
     * Updates an existing Department entity using values from the request DTO.
     *
     * The department ID is preserved to prevent primary key modification.
     * The department head is ignored because it is handled separately
     * in the service layer after validation.
     * The teachers collection is ignored because teacher assignments
     * are managed independently.
     */
    @Mapping(target = "departmentId", ignore = true)
    @Mapping(target = "departmentHead", ignore = true)
    @Mapping(target = "teachers", ignore = true)
    void updateEntity(@MappingTarget Department department, DepartmentRequest request);


}

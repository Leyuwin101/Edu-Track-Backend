package com.example.edutrackbackend.student.mapper;

import com.example.edutrackbackend.student.dto.StudentRequest;
import com.example.edutrackbackend.student.dto.StudentResponse;
import com.example.edutrackbackend.student.model.Student;
import com.example.edutrackbackend.user.mapper.UserMapper;
import com.example.edutrackbackend.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface StudentMapper {

    /**
     * Converts StudentRequest + User into Student entity.
     * Used when creating a new Student record with an already-resolved User.
     */
    @Mapping(target = "user", source = "user")
    Student toEntity(StudentRequest request, User user);

    /**
     * Converts Student entity into StudentResponse DTO.
     * Maps nested User entity into UserDTO using UserMapper#toUserDTO.
     */
    @Mapping(target = "userDTO", source = "user", qualifiedByName = "toUserDTO")
    StudentResponse toDto(Student student);

    /**
     * Updates an existing Student entity with values from StudentRequest.
     * Ignores system-managed fields and relationships.
     */
    @Mapping(target = "studentId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(@MappingTarget Student student, StudentRequest request);

}

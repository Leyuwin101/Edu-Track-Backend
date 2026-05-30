package com.example.edutrackbackend.teacher.mapper;

import com.example.edutrackbackend.teacher.dto.TeacherRequest;
import com.example.edutrackbackend.teacher.dto.TeacherResponse;
import com.example.edutrackbackend.teacher.model.Teacher;
import com.example.edutrackbackend.user.mapper.UserMapper;
import com.example.edutrackbackend.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface TeacherMapper {

    /**
     * Converts Teacher Request + User into Teacher entity
     * Used when creating new Teacher with an already-resolved user
     */
    @Mapping(target = "user", source = "user")
    Teacher toEntity(TeacherRequest request, User user);

    /**
     * Converts Teacher entity into TeacherResponse DTO.
     * Maps nested User entity into UserDTO using UserMapper#toUserDTO.
     */
    @Mapping(target = "userDTO", source = "user", qualifiedByName = "userDTO")
    TeacherResponse toDto(Teacher teacher);

    /**
     * Updates an existing Teacher entity with values from TeacherRequest.
     * Ignores system-managed fields and relationships.
     */
    @Mapping(target = "teacherId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(@MappingTarget Teacher teacher, TeacherRequest request);
}

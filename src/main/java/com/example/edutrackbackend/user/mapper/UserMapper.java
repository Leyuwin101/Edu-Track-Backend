package com.example.edutrackbackend.user.mapper;

import com.example.edutrackbackend.user.dto.UserDTO;
import com.example.edutrackbackend.user.dto.UserRequest;
import com.example.edutrackbackend.user.dto.UserResponse;
import com.example.edutrackbackend.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Converts UserRequest DTO into User entity.
     * Used during user registration or creation.
     */
    User toEntity(UserRequest request);

    /**
     * Converts User entity into full UserResponse DTO.
     * Used for API responses requiring complete user details.
     */
    UserResponse toDto(User user);

    /**
     * Converts User entity into lightweight UserDTO.
     * Used when embedding user data inside other DTOs (e.g., StudentResponse).
     */
    @Named("toUserDTO")
    UserDTO toUserDto(User user);


}

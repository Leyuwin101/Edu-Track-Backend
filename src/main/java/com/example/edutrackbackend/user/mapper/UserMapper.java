package com.example.edutrackbackend.user.mapper;

import com.example.edutrackbackend.user.dto.UserRequest;
import com.example.edutrackbackend.user.dto.UserResponse;
import com.example.edutrackbackend.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    /**
     * Takes a DTO (from API request) and builds an Entity for persistence.
     * @param request UserRequest
     * @return user
     */
    public User toEntity(UserRequest request) {

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        return user;
    }


    /**
     * Takes an Entity (from DB) and builds a DTO for API response.
     * @param user
     * @return User response
     */
    public UserResponse toDto(User user) {

        return new UserResponse(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt()
        );
    }

}

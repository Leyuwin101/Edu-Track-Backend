package com.example.edutrackbackend.user.service;

import com.example.edutrackbackend.user.dto.UserRequest;
import com.example.edutrackbackend.user.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest request);

    UserResponse updateUser(Long userId, UserRequest request);

    void deleteUser(Long userId);

    UserResponse getUserById(Long userId);

    List<UserResponse> getAllUsers();

}

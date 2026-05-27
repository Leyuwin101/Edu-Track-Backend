package com.example.edutrackbackend.user.service;

import com.example.edutrackbackend.user.dto.UserRequest;
import com.example.edutrackbackend.user.dto.UserResponse;
import com.example.edutrackbackend.user.mapper.UserMapper;
import com.example.edutrackbackend.user.model.User;
import com.example.edutrackbackend.user.repository.UserRepository;
import com.example.edutrackbackend.user.validation.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserValidator userValidator;

    /**
     * Create new user account
     *
     * Process:
     * - Use UserValidator for email uniqueness
     * - Maps the request DTO to User Entity(DB)
     * - Encodes the raw password using BCrypt
     * - Saves the user in the database
     *
     * @param request user registration data
     * @return saved user response
     */
    @Override
    public UserResponse createUser(UserRequest request) {

        log.info("[USER][CREATE] start username={}", request.getUsername());

        userValidator.validateEmailUnique(request.getEmail());

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User saved = userRepository.save(user);

        log.info("[USER][CREATE] success username={}", saved.getUsername());

        return userMapper.toDto(saved);

    }

    /**
     * Updates an existing user
     *
     * Process:
     * - Use UserValidator to validate if user exist
     * - And use it as well to valid email uniqueness in update
     * - Encodes the password if a new password is provided
     * - Save the updated user
     * - returns the updated user as a response dto
     * @param userId user id to update
     * @param request updated user data
     * @return updated user response
     */
    @Override
    public UserResponse updateUser(Long userId, UserRequest request) {

        log.info("[USER][UPDATE] Start userId={}", userId);

        User user = userValidator.validateUserExists(userId);

        userValidator.validateEmailUniqueForUpdate(user, request.getEmail());

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        User updated = userRepository.save(user);

        log.info("[USER][UPDATE] Success userId={}", userId);

        return userMapper.toDto(updated);
    }

    /**
     * Delete existing user
     *
     * Process:
     * - Use UserValidator to validate if user exists
     * - Delete the existing user
     *
     * @param userId user id to delete
     */
    @Override
    public void deleteUser(Long userId) {

        log.info("[USER][DELETE] Start userId={}", userId);

        User user = userValidator.validateUserExists(userId);

        userRepository.delete(user);

        log.info("[USER][DELETE] Success userId={}", userId);
    }

    /**
     * Fetch existing user by id
     *
     * Process:
     * - Use UserValidator to validates if user exists
     * - return the corresponding user id
     *
     * @param userId user id to fetch
     * @return user response
     */
    @Override
    public UserResponse getUserById(Long userId) {

        log.info("[USER][GET] Start userId={}", userId);

        User user = userValidator.validateUserExists(userId);

        log.info("[USER][GET] Success userId={}", userId);

        return userMapper.toDto(user);
    }

    /**
     * Fetch all the existing users
     *
     * Process:
     * - Retrieves all the users from the database using the repository
     *
     * @return user response
     */
    @Override
    public List<UserResponse> getAllUsers() {

        log.info("[USER][GET_ALL] Fetching all users");

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(userMapper::toDto)
                .toList();
    }





}

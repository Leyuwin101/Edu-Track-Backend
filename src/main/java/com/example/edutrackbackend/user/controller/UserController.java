package com.example.edutrackbackend.user.controller;

import com.example.edutrackbackend.common.response.ApiRes;
import com.example.edutrackbackend.common.response.ResponseFactory;
import com.example.edutrackbackend.user.dto.UserRequest;
import com.example.edutrackbackend.user.dto.UserResponse;
import com.example.edutrackbackend.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "User Management APIs")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /**
     * Creates a new user account.
     *
     * Accessible only by ADMIN users
     *
     * @param request user registration data
     * @return created user response
     */
    @Operation(summary = "Create new user", description = "Accessible by Admin only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully created"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "409", description = "Email Already Exists"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiRes<UserResponse>> createUser(@Valid @RequestBody UserRequest request) {

        UserResponse user = userService.createUser(request);

        return ResponseFactory.created("User Created Successfully", user);
    }


    /**
     * Updates an Existing user
     *
     * Accessibly By ADMIN users
     *
     * @param userId user id to update
     * @param request updated user data
     * @return updated user response
     */
    @Operation(summary = "Update user by id", description = "Accessible by Admin only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "409", description = "Email already exists"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiRes<UserResponse>> updateUser(@PathVariable("id") Long userId, @Valid @RequestBody UserRequest request) {

        UserResponse updated = userService.updateUser(userId, request);

        return ResponseFactory.success("User updated successfully", updated);
    }

    /**
     * Delete the existing user account
     *
     * Accessibly by ADMIN users
     *
     * @param userId user id to delete
     * @return no content
     */
    @Operation(summary = "Delete user by ID", description = "Accessible by ADMIN only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiRes<Void>> deleteUser(@PathVariable("id") Long userId) {

        userService.deleteUser(userId);

        return ResponseEntity.noContent().build();
    }

    /**
     * Fetch a single user by id
     *
     * Accessibly by ADMIN users
     *
     * @param userId user id to fetch
     * @return user response
     */
    @Operation(summary = "Get user by id", description = "Accessible by ADMIN only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User fetched successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiRes<UserResponse>> getUserById(@PathVariable("id") Long userId) {

        UserResponse user = userService.getUserById(userId);

        return ResponseFactory.success("User fetched successfully", user);
    }

    /**
     * Fetch all registered users
     *
     * Accessible by ADMIN only
     *
     * @return list of users
     */
    @Operation(summary = "Fetch all users", description = "Accessible by ADMIN only")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All users fetched successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiRes<List<UserResponse>>> getAllUsers() {

        List<UserResponse> users = userService.getAllUsers();

        return ResponseFactory.success("All users fetched", users);
    }





}

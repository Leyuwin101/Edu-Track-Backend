package com.example.edutrackbackend.auth.validator;

import com.example.edutrackbackend.auth.dto.AuthRequest;
import com.example.edutrackbackend.auth.exception.AuthException;
import com.example.edutrackbackend.user.model.User;
import com.example.edutrackbackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthValidator {

    private final UserRepository userRepository;

    // Validate login request
    public void validateLoginRequest(AuthRequest request) {

        if (request == null || request.getEmail() == null || request.getEmail().isBlank() ||
            request.getPassword() == null || request.getPassword().isBlank()) {
            throw new AuthException("Email and Password are required");
        }

    }

    // Validate user role
    public void validateUserRole(User user) {

        if (user.getRole() == null) throw new AuthException("User role not configured");
    }

    // Validate email exists
    public User validateEmailExists(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new AuthException("Invalid credentials"));

    }

}

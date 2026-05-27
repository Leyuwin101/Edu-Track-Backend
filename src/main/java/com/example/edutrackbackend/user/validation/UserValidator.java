package com.example.edutrackbackend.user.validation;

import com.example.edutrackbackend.common.exception.EmailAlreadyExistsException;
import com.example.edutrackbackend.user.exception.UserNotFoundException;
import com.example.edutrackbackend.user.model.User;
import com.example.edutrackbackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    // Validate is the user exist
    public User validateUserExists(Long userId) {

        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + userId));
    }

    // Validate if the email is unique
    public void validateEmailUnique(String email) {

        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("Email already exists: " + email);
        }
    }

    // Validate if the updated email is unique
    public void validateEmailUniqueForUpdate(User user, String newEmail) {

        if (!user.getEmail().equals(newEmail) && userRepository.existsByEmail(newEmail)) {

            throw new EmailAlreadyExistsException("Email already exists: " + newEmail);
        }
    }

}

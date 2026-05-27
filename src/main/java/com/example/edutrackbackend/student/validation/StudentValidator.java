package com.example.edutrackbackend.student.validation;

import com.example.edutrackbackend.common.exception.EmailAlreadyExistsException;
import com.example.edutrackbackend.student.exception.StudentNotFoundException;
import com.example.edutrackbackend.student.model.Student;
import com.example.edutrackbackend.student.repository.StudentRepository;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentValidator {

    private final StudentRepository studentRepository;

    // Validate if student exist
    public void validateStudentExists(Long userId) {

        Student student = studentRepository.findById(userId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found: " + userId));
    }

    // Validate if the student number is unique
    public void validateStudentNumberUnique(String studentNumber) {

        boolean exists = studentRepository.existsByStudentNumber(studentNumber);

        if (exists) throw new DuplicateRequestException("Student number already exists: " + studentNumber);
    }

    // Validate if the email is unique
    public void validateEmailUnique(String email) {

        if (studentRepository.existsByEmail(email)) throw new EmailAlreadyExistsException("Email already exists: " + email);
    }

    public void validateUpdateEmailUnique(Student student, String newEmail) {

        if (!student.getEmail().equals(newEmail) && studentRepository.existsByEmail(newEmail)) {

            throw new EmailAlreadyExistsException("Email Already exists: " + newEmail);
        }
    }
}

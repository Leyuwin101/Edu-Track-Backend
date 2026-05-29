package com.example.edutrackbackend.student.validation;

import com.example.edutrackbackend.common.exception.SectionNotFoundException;
import com.example.edutrackbackend.student.exception.StudentNotFoundException;
import com.example.edutrackbackend.student.model.Student;
import com.example.edutrackbackend.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentValidator {

    private final StudentRepository studentRepository;

    // Validate if student exist
    public Student validateStudentExists(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found: " + studentId));

        return student;
    }



    // Validate if section exists
    public void validateSectionExists(String section) {

        boolean exists = studentRepository.existsBySection(section);

        if (!exists) throw new SectionNotFoundException("Section not found: " + section);
    }
}

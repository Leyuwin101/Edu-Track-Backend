package com.example.edutrackbackend.teacher.validation;

import com.example.edutrackbackend.teacher.exception.TeacherNotFoundException;
import com.example.edutrackbackend.teacher.model.Teacher;
import com.example.edutrackbackend.teacher.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherValidator {

    private final TeacherRepository teacherRepository;

    // Validate if teacher exists
    public Teacher validateTeacherExists(Long teacherId) {

        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found: " + teacherId));
    }
}

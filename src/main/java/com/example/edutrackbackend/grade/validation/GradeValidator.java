package com.example.edutrackbackend.grade.validation;

import com.example.edutrackbackend.grade.exception.GradeNotFoundException;
import com.example.edutrackbackend.grade.model.Grade;
import com.example.edutrackbackend.grade.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GradeValidator {

    private final GradeRepository gradeRepository;

    public Grade validateGradeExists(Long gradeId) {

        return gradeRepository.findById(gradeId)
                .orElseThrow(() -> new GradeNotFoundException("Grade not found: " + gradeId));
    }
}

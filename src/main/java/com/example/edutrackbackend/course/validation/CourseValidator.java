package com.example.edutrackbackend.course.validation;

import com.example.edutrackbackend.course.exception.CourseNotFoundException;
import com.example.edutrackbackend.course.model.Course;
import com.example.edutrackbackend.course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseValidator {

    private final CourseRepository courseRepository;

    // Course validator if exists
    public Course validateCourseExists(Long courseId) {

        return courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found: " + courseId));
    }
}

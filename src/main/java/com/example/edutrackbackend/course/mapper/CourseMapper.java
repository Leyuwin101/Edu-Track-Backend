package com.example.edutrackbackend.course.mapper;

import com.example.edutrackbackend.course.dto.CourseDTO;
import com.example.edutrackbackend.course.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Named("toCourseDTO")
    CourseDTO toCourseDto(Course course);
}

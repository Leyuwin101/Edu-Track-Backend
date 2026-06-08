package com.example.edutrackbackend.grade.mapper;

import com.example.edutrackbackend.course.mapper.CourseMapper;
import com.example.edutrackbackend.course.model.Course;
import com.example.edutrackbackend.grade.dto.GradeRequest;
import com.example.edutrackbackend.grade.dto.GradeResponse;
import com.example.edutrackbackend.grade.model.Grade;
import com.example.edutrackbackend.student.mapper.StudentMapper;
import com.example.edutrackbackend.student.model.Student;
import com.example.edutrackbackend.teacher.mapper.TeacherMapper;
import com.example.edutrackbackend.teacher.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {
        StudentMapper.class,
        CourseMapper.class,
        TeacherMapper.class
})
public interface GradeMapper {

    @Mapping(target = "student", source = "student")
    @Mapping(target = "course", source = "course")
    @Mapping(target = "teacher", source = "teacher")
    @Mapping(target = "createdAt", ignore = true)
    Grade toEntity(GradeRequest request, Student student, Course course, Teacher teacher);

    @Mapping(target = "student", source = "student", qualifiedByName = "toStudentDTO")
    @Mapping(target = "course", source = "course", qualifiedByName = "toCourseDTO")
    @Mapping(target = "teacher", source = "teacher", qualifiedByName = "toTeacherDTO")
    GradeResponse toDto(Grade grade);

    @Mapping(target = "gradeId", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntity(@MappingTarget Grade grade, GradeRequest request);

}

package com.example.edutrackbackend.grade.repository;

import com.example.edutrackbackend.grade.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    List<Grade> getGradeByStudentId(Long studentId);

    List<Grade> getGradeByCourseId(Long courseId);

    List<Grade> getGradeByTeacherId(Long teacherId);
}

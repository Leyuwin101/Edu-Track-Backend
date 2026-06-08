package com.example.edutrackbackend.grade.model;

import com.example.edutrackbackend.course.model.Course;
import com.example.edutrackbackend.student.model.Student;
import com.example.edutrackbackend.teacher.model.Teacher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "grades",
        indexes = {
                @Index(name = "idx_grades_student", columnList = "student")

        }
)
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private Long gradeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @Column(name = "score")
    private Double score;

    @Column(name = "remarks")
    private String remarks;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}

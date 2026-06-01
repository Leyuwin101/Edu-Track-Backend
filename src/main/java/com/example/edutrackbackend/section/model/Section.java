package com.example.edutrackbackend.section.model;

import com.example.edutrackbackend.course.model.Course;
import com.example.edutrackbackend.student.enums.YearLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sections")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    private Long sectionId;

    @Column(name = "section_code", nullable = false, unique = true)
    private String sectionCode;

    @Column(name = "section_name", nullable = false)
    private String sectionName;

    @Column(name = "year_level", nullable = false)
    private YearLevel yearLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
}

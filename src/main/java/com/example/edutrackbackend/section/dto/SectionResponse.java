package com.example.edutrackbackend.section.dto;

import com.example.edutrackbackend.course.dto.CourseDTO;
import com.example.edutrackbackend.student.enums.YearLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SectionResponse {

    private Long sectionId;

    private String sectionCode;

    private String sectionName;

    private YearLevel yearLevel;

    private CourseDTO course;
}

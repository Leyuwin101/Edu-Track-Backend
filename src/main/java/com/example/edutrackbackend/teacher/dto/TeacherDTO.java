package com.example.edutrackbackend.teacher.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {

    private Long teacherId;

    private String firstName;

    private String lastName;
}

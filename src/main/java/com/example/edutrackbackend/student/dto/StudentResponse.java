package com.example.edutrackbackend.student.dto;

import com.example.edutrackbackend.common.enums.Gender;
import com.example.edutrackbackend.section.dto.SectionDTO;
import com.example.edutrackbackend.student.enums.YearLevel;
import com.example.edutrackbackend.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {

    private Long studentId;

    private UserDTO userDTO;

    private String studentNumber;

    private String firstName;

    private String middleName;

    private String lastName;

    private Gender gender;

    private LocalDate birthDate;

    private YearLevel yearLevel;

    private SectionDTO section;

    private String contactNumber;

    private String address;

    private String guardianName;

    private String guardianContact;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

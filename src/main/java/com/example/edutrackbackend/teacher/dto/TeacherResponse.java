package com.example.edutrackbackend.teacher.dto;

import com.example.edutrackbackend.common.enums.Gender;
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
public class TeacherResponse {

    private Long teacherId;

    private UserDTO user;

    private String employeeNumber;

    private String firstName;

    private String middleName;

    private String lastName;

    private Gender gender;

    private LocalDate birthdate;

    private String address;

    private String department;

    private String specialization;

    private String contactNumber;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}

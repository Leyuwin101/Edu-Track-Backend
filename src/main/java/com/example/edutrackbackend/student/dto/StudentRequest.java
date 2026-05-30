package com.example.edutrackbackend.student.dto;

import com.example.edutrackbackend.common.enums.Gender;
import com.example.edutrackbackend.student.enums.YearLevel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Student request")
public class StudentRequest {

    @NotNull(message = "User id is required")
    private Long userId;

    @Size(max = 100, message = "First name too long")
    @NotBlank(message = "First name is required")
    private String firstName;

    private String middleName;

    @Size(max = 100, message = "Last name too long")
    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotNull(message = "Birth Date is required")
    private LocalDate birthDate;

    @NotNull(message = "Year Level is required")
    private YearLevel yearLevel;

    private String section;

    @Pattern(
            regexp = "^09\\d{9}$",
            message = "Invalid Philippine Contact number"
    )
    @NotBlank(message = "Contact number is required")
    private String contactNumber;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Guardian Name is required")
    private String guardianName;

    @Pattern(
            regexp = "^09\\d{9}$",
            message = "Invalid Philippine Contact number"
    )
    @NotBlank(message = "Guardian Contact is required")
    private String guardianContact;


}

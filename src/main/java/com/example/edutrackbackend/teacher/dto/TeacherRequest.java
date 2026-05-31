package com.example.edutrackbackend.teacher.dto;

import com.example.edutrackbackend.common.enums.Gender;
import com.example.edutrackbackend.department.dto.DepartmentDTO;
import com.example.edutrackbackend.department.model.Department;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRequest {

    @NotNull(message = "User id is required")
    private Long userId;

    @Size(max = 100, message = "First name is too long")
    @NotBlank(message = "First name is required")
    private String firstName;

    private String middleName;

    @Size(max = 100, message = "Last name is too long")
    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotNull(message = "Birth date is required")
    private LocalDate birthdate;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Specialization is required")
    private String specialization;

    @Pattern(
            regexp = "^09\\d{9}$",
            message = "Invalid philippine contact number"
    )
    @NotBlank(message = "Contact number is required")
    private String contactNumber;


}

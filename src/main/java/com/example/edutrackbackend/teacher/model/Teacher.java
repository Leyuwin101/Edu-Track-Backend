package com.example.edutrackbackend.teacher.model;

import com.example.edutrackbackend.common.enums.Gender;
import com.example.edutrackbackend.department.model.Department;
import com.example.edutrackbackend.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "teachers",
        indexes = {
                @Index(name = "idx_teachers_employee_number", columnList = "employee_number"),
                @Index(name = "idx_teachers_last_name", columnList = "last_name"),
                @Index(name = "idx_teachers_department_id", columnList = "department_id")
        }
)
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long teacherId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "employee_number", nullable = false, unique = true)
    private String employeeNumber;

    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "address")
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "specialization", nullable = false)
    private String specialization;

    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    @PrePersist
    public void prePersist() { this.createdAt = LocalDateTime.now(); }
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;




}

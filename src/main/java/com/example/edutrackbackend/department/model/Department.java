package com.example.edutrackbackend.department.model;

import com.example.edutrackbackend.teacher.model.Teacher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(
        name = "departments",
        indexes = {
                @Index(name = "idx_departments_department_name", columnList = "department_name")
        }
)
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "department_name", nullable = false, unique = true)
    private String departmentName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_head_id")
    private Teacher departmentHead;

    @OneToMany(mappedBy = "department")
    private List<Teacher> teachers;

}

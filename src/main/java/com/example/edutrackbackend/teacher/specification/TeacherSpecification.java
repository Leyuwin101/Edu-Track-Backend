package com.example.edutrackbackend.teacher.specification;

import com.example.edutrackbackend.common.enums.Gender;
import com.example.edutrackbackend.department.model.Department;
import com.example.edutrackbackend.teacher.model.Teacher;
import org.springframework.data.jpa.domain.Specification;

public class TeacherSpecification {

    /**
     * Search Teacher using
     * - First name
     * - Last name
     * - Employee number
     */
    public static Specification<Teacher> hasKeyword(String keyword) {

        return (root, query, cb ) -> {

            if (keyword == null || keyword.isBlank()) return null;

            String search = "%" + keyword.toLowerCase() + "%";

            return cb.or(
                    cb.like(cb.lower(root.get("firstName")), search),
                    cb.like(cb.lower(root.get("lastName")), search),
                    cb.like(cb.lower(root.get("employeeNumber")), search)

            );
        };
    }

    // Filter Teacher by Department
    public static Specification<Teacher> hasDepartment(Long departmentId) {

        return (root, query, cb) -> {

            if (departmentId == null) return null;

            return cb.equal(root.get("department").get("departmentId"), departmentId);
        };
    }

    // Filter Teacher by Gender
    public static Specification<Teacher> hasGender(Gender gender) {

        return (root, query, cb ) -> {

            if (gender == null) return null;

            return cb.equal(root.get("gender"), gender);
        };
    }

    // Filter Teacher by Specialization
    public static Specification<Teacher> hasSpecialization(String specialization) {

        return (root, query, cb ) -> {

            if (specialization == null) return null;

            return cb.equal(root.get("specialization"), specialization);
        };
    }
}

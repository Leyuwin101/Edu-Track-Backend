package com.example.edutrackbackend.student.specification;

import com.example.edutrackbackend.student.enums.StudentStatus;
import com.example.edutrackbackend.student.enums.YearLevel;
import com.example.edutrackbackend.student.model.Student;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification {

    /**
     * Search student using:
     * - first name,
     * - last name,
     * - student number,
     * - email
     */
    public static Specification<Student> hasKeyword(String keyword) {

        return (root, query, cb) -> {

            if (keyword == null || keyword.isBlank()) return null;

            String search = "%" + keyword.toLowerCase() + "%";

            return cb.or(
                    cb.like(cb.lower(root.get("firstName")), search),
                    cb.like(cb.lower(root.get("lastName")), search),
                    cb.like(cb.lower(root.get("studentNumber")), search),
                    cb.like(cb.lower(root.get("email")), search)

            );
        };
    }

    // Filter students by year level
    public static Specification<Student> hasYearLevel(YearLevel yearLevel) {

        return (root, query, cb) -> {

            if (yearLevel == null) return null;

            return cb.equal(root.get("yearLevel"), yearLevel);
        };
    }

    // Filter students by status
    public static Specification<Student> hasStatus(StudentStatus status) {

        return (root, query, cb) -> {

            if (status == null) return null;

            return cb.equal(root.get("status"), status);
        };
    }


    // Filter students by section
    public static Specification<Student> hasSection(String section) {

        return (root, query, cb) -> {

            if (section == null) return null;

            return cb.equal(cb.lower(root.get("section")), section.toLowerCase());
        };
    }
}

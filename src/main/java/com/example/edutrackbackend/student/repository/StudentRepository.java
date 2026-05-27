package com.example.edutrackbackend.student.repository;

import com.example.edutrackbackend.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

    Optional<Student> findByStudentNumber(String studentNumber);

    List<Student> findAllByLastName(String lastName);

    boolean existsByStudentNumber(String number);

}

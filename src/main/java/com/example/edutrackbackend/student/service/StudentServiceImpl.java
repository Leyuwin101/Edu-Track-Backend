package com.example.edutrackbackend.student.service;

import com.example.edutrackbackend.student.dto.StudentRequest;
import com.example.edutrackbackend.student.dto.StudentResponse;
import com.example.edutrackbackend.student.mapper.StudentMapper;
import com.example.edutrackbackend.student.model.Student;
import com.example.edutrackbackend.student.repository.StudentRepository;
import com.example.edutrackbackend.student.validation.StudentValidator;
import com.example.edutrackbackend.user.model.User;
import com.example.edutrackbackend.user.validation.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final StudentValidator studentValidator;
    private final UserValidator userValidator;

    /**
     * Create student that links with user
     *
     * Process:
     * - Use student validator to check if the student number is unique
     * - Use user validator to check if the user id exists
     * - Map the Request Dto to Student Entity(DB)
     * - Saved the student in the database
     *
     * @param request Student registration data
     * @return saved student response
     */
    @Override
    public StudentResponse createStudent(StudentRequest request) {

        log.info("[STUDENT][CREATE] start lastName={}", request.getLastName() );

        studentValidator.validateStudentNumberUnique(request.getStudentNumber());

        User user = userValidator.validateUserExists(request.getUserId());

        Student student = studentMapper.toEntity(request, user);

        Student saved = studentRepository.save(student);

        log.info("[STUDENT][CREATE] success lastName={}", saved.getLastName());

        return studentMapper.toDto(saved);
    }

    /**
     * Update existing student
     *
     * Process:
     * - Use student validator to check if the student exists
     * - Use the student mapper for the update entity
     * - Save the updated student
     * - Return the updated student response
     *
     * @param studentId student id to update
     * @param request student updated registration
     * @return updated student
     */
    @Override
    public StudentResponse updateStudent(Long studentId, StudentRequest request) {

        log.info("[STUDENT][UPDATE] start studentId={}", studentId);

        Student student = studentValidator.validateStudentExists(studentId);

        studentMapper.updateEntity(student, request);

        Student updated = studentRepository.save(student);

        log.info("[PRODUCT][UPDATE] Success studentId={}", studentId );

        return studentMapper.toDto(updated);

    }

    /**
     * Delete existing student
     *
     * Process:
     * - Use student validator to check if the student exists
     * - Delete the student
     *
     * @param studentId student id to delete
     */
    @Override
    public void deleteStudent(Long studentId) {

        log.info("[STUDENT][DELETE] Start studentId={}", studentId);

        Student student = studentValidator.validateStudentExists(studentId);

        studentRepository.delete(student);

        log.info("[STUDENT][DELETE] Success studentId={}", studentId);
    }

    /**
     * Fetch existing student by id
     *
     * Process:
     * - Use student validator to check if the student exists
     * - Return the student
     *
     * @param studentId student id to fetch
     * @return student response
     */
    @Override
    public StudentResponse getStudentById(Long studentId) {

        log.info("[STUDENT][GET] Start studentId={}", studentId);

        Student student = studentValidator.validateStudentExists(studentId);

        log.info("[STUDENT][GET] Success studentId={}", studentId);

        return studentMapper.toDto(student);
    }

    /**
     * Fetch all the existing students
     *
     * Process:
     * - Retrieves all the students from the database using the repository
     *
     * @return List of students
     */
    @Override
    public List<StudentResponse> getAllStudents() {

        log.info("[STUDENT][GET_ALL] Fetching all students");

        List<Student> students = studentRepository.findAll();

        return students.stream()
                .map(studentMapper::toDto)
                .toList();
    }



}

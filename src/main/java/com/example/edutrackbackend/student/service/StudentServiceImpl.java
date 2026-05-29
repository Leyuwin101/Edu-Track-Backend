package com.example.edutrackbackend.student.service;

import com.example.edutrackbackend.common.response.PaginatedRes;
import com.example.edutrackbackend.student.dto.StudentRequest;
import com.example.edutrackbackend.student.dto.StudentResponse;
import com.example.edutrackbackend.student.enums.StudentStatus;
import com.example.edutrackbackend.student.mapper.StudentMapper;
import com.example.edutrackbackend.student.model.Student;
import com.example.edutrackbackend.student.repository.StudentRepository;
import com.example.edutrackbackend.student.specification.StudentFilter;
import com.example.edutrackbackend.student.specification.StudentSpecification;
import com.example.edutrackbackend.student.validation.StudentValidator;
import com.example.edutrackbackend.user.model.User;
import com.example.edutrackbackend.user.validation.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
     * - Set the student number with generated student id (EDU-2026-0001)
     * - Save the updated student with student number in database
     *
     * @param request Student registration data
     * @return saved student response
     */
    @Override
    public StudentResponse createStudent(StudentRequest request) {

        log.info("[STUDENT][CREATE] start lastName={}", request.getLastName() );

        User user = userValidator.validateUserExists(request.getUserId());

        Student student = studentMapper.toEntity(request, user);

        Student saved = studentRepository.save(student);

        String studentNumber = "EDU-" + LocalDate.now().getYear() + "-" + String.format("%04d", saved.getStudentId());

        saved.setStudentNumber(studentNumber);

        Student updated = studentRepository.save(saved);

        log.info("[STUDENT][CREATE] success lastName={}", updated.getLastName());

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


    /**
     * Search students
     *
     * Process:
     * - Use StudentSpecification for the filtered keywords
     * - Find all the students with the keyword
     * - Map all the students into dto
     * - Return the paginated response of the students
     *
     * @param filter Student filter (search conditions)
     * @param pageable Pagination (page number, page size, sorting)
     * @return Paginated Response Students
     */
    @Override
    public PaginatedRes<StudentResponse> searchStudents(StudentFilter filter, Pageable pageable) {

        log.info("[STUDENT][SEARCH] Start");

        // Specification for searching students
        Specification<Student> spec = StudentSpecification.hasKeyword(filter.getKeyword())
                .and(StudentSpecification.hasYearLevel(filter.getYearLevel()))
                .and(StudentSpecification.hasStatus(filter.getStatus()))
                .and(StudentSpecification.hasGender(filter.getGender()))
                .and(StudentSpecification.hasSection(filter.getSection()));

        // Pagination of students with the specification
        Page<Student> students = studentRepository.findAll(spec, pageable);

        // Map all the students into dto
        List<StudentResponse> responses = students.getContent()
                .stream()
                .map(studentMapper::toDto)
                .toList();

        log.info("[STUDENT][SEARCH] Success");

        return PaginatedRes.<StudentResponse>builder()
                .status("success")
                .data(responses)
                .currentPage(students.getNumber())
                .totalPage(students.getTotalPages())
                .totalItems(students.getTotalPages())
                .build();
    }

    /**
     * Assign Section
     *
     * Process:
     * - Use Student Validator to check if the students exist first
     * - Set the section
     * - Saved the assigned section of the student
     * - Return the student response
     *
     * @param studentId student id to assign
     * @param section section of the student
     * @return updated student response with the section
     */
    @Override
    public StudentResponse assignSection(Long studentId, String section) {

        log.info("[STUDENT][ASSIGN_SECTION] Start studentId={}", studentId);

        Student student = studentValidator.validateStudentExists(studentId);

        student.setSection(section);

        Student saved = studentRepository.save(student);

        log.info("[STUDENT][ASSIGN_SECTION] Success studentId={}, section={}", saved.getStudentId(), saved.getSection());

        return studentMapper.toDto(saved);

    }

    /**
     * Update Student Status
     *
     * Process:
     * - Use Student Validator to check if the students exist first
     * - Update the status
     * - Saved the updated status of the student
     * - Return the updated student response
     *
     * @param studentId student id to update status
     * @param status status to update
     * @return Updated student response
     */
    @Override
    public StudentResponse updateStatus(Long studentId, StudentStatus status) {

        log.info("[STUDENT][UPDATE_STATUS] Start studentId={}", studentId);

        Student student = studentValidator.validateStudentExists(studentId);

        student.setStatus(status);

        Student saved = studentRepository.save(student);

        log.info("[STUDENT][UPDATE_STATUS) Success studentId={}, status={}", saved.getStudentId(), saved.getStatus());

        return studentMapper.toDto(saved);

    }



}

package com.example.edutrackbackend.grade.service;

import com.example.edutrackbackend.course.model.Course;
import com.example.edutrackbackend.course.validation.CourseValidator;
import com.example.edutrackbackend.grade.dto.GradeRequest;
import com.example.edutrackbackend.grade.dto.GradeResponse;
import com.example.edutrackbackend.grade.mapper.GradeMapper;
import com.example.edutrackbackend.grade.model.Grade;
import com.example.edutrackbackend.grade.repository.GradeRepository;
import com.example.edutrackbackend.grade.validation.GradeValidator;
import com.example.edutrackbackend.student.model.Student;
import com.example.edutrackbackend.student.validation.StudentValidator;
import com.example.edutrackbackend.teacher.model.Teacher;
import com.example.edutrackbackend.teacher.validation.TeacherValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;
    private final GradeValidator gradeValidator;
    private final StudentValidator studentValidator;
    private final TeacherValidator teacherValidator;
    private final CourseValidator courseValidator;

    /**
     * Create grade
     *
     * Process:
     * - Validate if Student, Course, Teacher exists
     * - Map the Request Dto to Grade Entity(DB)
     * - Save the grade to the database
     *
     * @param request Grade registration data
     * @return saved grade response
     */
    @Override
    public GradeResponse createGrade(GradeRequest request) {

        log.info("[GRADE][CREATE] Start studentId={}", request.getStudentId());

        Student student = studentValidator.validateStudentExists(request.getStudentId());
        Course course = courseValidator.validateCourseExists(request.getCourseId());
        Teacher teacher = teacherValidator.validateTeacherExists(request.getTeacherId());

        Grade grade = gradeMapper.toEntity(request, student, course, teacher);

        Grade saved = gradeRepository.save(grade);

        log.info("[GRADE][CREATE] Success studentId={}", saved.getStudent());

        return gradeMapper.toDto(saved);

    }

    /**
     * Update existing grade
     *
     * Process:
     * - Validate if grade exists
     * - Use grade mapper for the update entity
     * - Save the updated grade
     *
     * @param gradeId grade id to update
     * @param request grade updated registration
     * @return updated grade
     */
    @Override
    public GradeResponse updateGrade(Long gradeId, GradeRequest request) {

        log.info("[GRADE][UPDATE] Start gradeId={}", gradeId);

        Grade grade = gradeValidator.validateGradeExists(gradeId);

        gradeMapper.updateEntity(grade, request);

        Grade saved = gradeRepository.save(grade);

        log.info("[GRADE][UPDATE] Success gradeId={}", gradeId);

        return gradeMapper.toDto(saved);
    }

    /**
     * Delete existing grade
     *
     * Process:
     * - Validate if the grade exists
     * - Delete the grade
     *
     * @param gradeId grade to delete
     */
    @Override
    public void deleteGrade(Long gradeId) {

        log.info("[GRADE][DELETE] Start gradeId={}", gradeId);

        Grade grade = gradeValidator.validateGradeExists(gradeId);

        gradeRepository.delete(grade);

        log.info("[GRADE][DELETE] Success gradeId={}", gradeId);
    }

    /**
     * Fetch existing grade by  id
     *
     * Process:
     * - Validate if grade exists
     * - Return the grade
     *
     * @param gradeId grade id to fetch
     * @return grade response
     */
    @Override
    public GradeResponse getGradeById(Long gradeId) {

        log.info("[GRADE][GET] Start gradeId={}", gradeId);

        Grade grade = gradeValidator.validateGradeExists(gradeId);

        log.info("[GRADE][GET] Success gradeId={}", gradeId);

        return gradeMapper.toDto(grade);

    }

    /**
     * Fetch all the existing grades
     *
     * Process:
     * - Retrieves all the grades from the database
     *
     * @return List of grades
     */
    @Override
    public List<GradeResponse> getAllGrades() {

        log.info("[GRADE][GET_ALL] Fetching all grades");

        List<Grade> grades = gradeRepository.findAll();

        return grades.stream()
                .map(gradeMapper::toDto)
                .toList();
    }

    /**
     * Fetch all grades of student
     *
     * Process:
     * - Retrieves all the grades of the student from the database
     *
     * @param studentId student id to fetch
     * @return List of grades of student
     */
    @Override
    public List<GradeResponse> getGradeByStudent(Long studentId) {

        log.info("[GRADE][GET_STUDENT] Start studentId={}", studentId);

        List<Grade> grades = gradeRepository.getGradeByStudentId(studentId);

        return grades.stream()
                .map(gradeMapper::toDto)
                .toList();
    }

    /**
     * Fetch all grades by Course
     *
     * Process:
     * - Retrieves all the grades per course from the database
     *
     * @param courseId course id to fetch
     * @return List of grades per course
     */
    @Override
    public List<GradeResponse> getGradeByCourse(Long courseId) {

        log.info("[GRADE][GET_COURSE] Start courseId={}", courseId);

        List<Grade> grades = gradeRepository.getGradeByCourseId(courseId);

        return grades.stream()
                .map(gradeMapper::toDto)
                .toList();
    }

    /**
     * Fetch all grades by teacher
     *
     * Process:
     * - Retrieves all the grades per teachers from the database
     *
     * @param teacherId teacher id to fetch the grades
     * @return List of grades per teacher
     */
    @Override
    public List<GradeResponse> getGradeByTeacher(Long teacherId) {

        log.info("[GRADE][GET_TEACHER] Start teacherId={}", teacherId);

        List<Grade> grades = gradeRepository.getGradeByTeacherId(teacherId);

        return grades.stream()
                .map(gradeMapper::toDto)
                .toList();
    }

    /**
     * Calculate remarks
     *
     * Process:
     * - If the grade is null return INC
     * - If the grade is greater than or equal to 75 return PASSED else FAILED
     *
     * @param grade grade to calculate
     * @return Remarks
     */
    @Override
    public String calculateRemarks(Double grade) {

        if (grade == null) {
            return "INC";
        }

        return grade >= 75 ? "PASSED" : "FAILED";
    }

}

package com.example.edutrackbackend.teacher.service;

import com.example.edutrackbackend.common.response.PaginatedRes;
import com.example.edutrackbackend.teacher.dto.TeacherRequest;
import com.example.edutrackbackend.teacher.dto.TeacherResponse;
import com.example.edutrackbackend.teacher.mapper.TeacherMapper;
import com.example.edutrackbackend.teacher.model.Teacher;
import com.example.edutrackbackend.teacher.repository.TeacherRepository;
import com.example.edutrackbackend.teacher.specification.TeacherFilter;
import com.example.edutrackbackend.teacher.specification.TeacherSpecification;
import com.example.edutrackbackend.teacher.validation.TeacherValidator;
import com.example.edutrackbackend.user.model.User;
import com.example.edutrackbackend.user.validation.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherServiceImpl implements TeacherService{

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final TeacherValidator teacherValidator;
    private final UserValidator userValidator;

    /**
     * Create new teacher
     *
     * Process:
     * - Use validator to check if user exists
     * - Map the request dto to Teacher Entity(db)
     * - Save the teacher to the database
     *
     * @param request teacher registration data
     * @return saved teacher response
     */
    @Override
    public TeacherResponse createTeacher(TeacherRequest request) {

        log.info("[TEACHER][CREATE] Start lastName={}", request.getLastName());

        User user = userValidator.validateUserExists(request.getUserId());

        Teacher teacher = teacherMapper.toEntity(request, user);

        Teacher saved  = teacherRepository.save(teacher);

        log.info("[TEACHER][CREATE] Success lastName={}", saved.getLastName());

        return teacherMapper.toDto(saved);

    }

    /**
     * Update teacher
     *
     * Process:
     * - Use validator to check if teacher exists
     * - Map the teacher for update entity
     * - Saved the updated teacher
     *
     * @param teacherId teacher id to update
     * @param request updated teacher registration data
     * @return updated teacher response
     */
    @Override
    public TeacherResponse updateTeacher(Long teacherId, TeacherRequest request) {

        log.info("[TEACHER][UPDATE] Start teacherId={}", teacherId);

        Teacher teacher = teacherValidator.validateTeacherExists(teacherId);

        teacherMapper.updateEntity(teacher, request);

        Teacher saved = teacherRepository.save(teacher);

        log.info("[TEACHER][UPDATE] Success teacherId={}", saved.getTeacherId());

        return teacherMapper.toDto(saved);
    }

    /**
     * Delete Teacher
     *
     * Process:
     * - Use validator to check if the teacher id exists
     * - Delete the teacher
     *
     * @param teacherId teacher id to delete
     */
    @Override
    public void deleteTeacher(Long teacherId) {

        log.info("[TEACHER][DELETE] Start teacherId={}", teacherId);

        Teacher teacher = teacherValidator.validateTeacherExists(teacherId);

        teacherRepository.delete(teacher);

        log.info("[TEACHER][DELETE] Success teacherId={}", teacherId);
    }


    /**
     * Fetch existing teacher id
     *
     * Process:
     * - Validate if the teacher id exists
     *
     * @param teacherId teacher id to fetch
     * @return teacher response
     */
    @Override
    public TeacherResponse getTeacherById(Long teacherId) {

        log.info("[TEACHER][GET] Start teacherId={}", teacherId);

        Teacher teacher = teacherValidator.validateTeacherExists(teacherId);

        log.info("[TEACHER][GET] Success teacherId={}", teacherId);

        return teacherMapper.toDto(teacher);
    }


    /**
     * Fetch all teachers
     *
     * Process:
     * - Retrieves all the teachers from the database using the repository
     *
     * @return list of teachers
     */
    @Override
    public List<TeacherResponse> getAllTeachers() {

        log.info("[TEACHER][GET_ALL] Fetching all teachers");

        List<Teacher> teachers =  teacherRepository.findAll();

        return teachers.stream()
                .map(teacherMapper::toDto)
                .toList();
    }

    /**
     * Search teachers
     *
     * Process:
     * - Use TeacherSpecification for the filtered keywords
     * - Find all the teachers with the keyword
     * - Map all the teachers into dto
     *
     * @param filter Teacher filter (search conditions)
     * @param pageable Pagination (page number, page size, sorting)
     * @return Paginated Response Teacher
     */
    @Override
    public PaginatedRes<TeacherResponse> searchTeacher(TeacherFilter filter, Pageable pageable) {

        log.info("[TEACHER][SEARCH] Start");

        // Specification for searching students
        Specification<Teacher> spec = TeacherSpecification.hasKeyword(filter.getKeyword())
                .and(TeacherSpecification.hasDepartment(filter.getDepartment()))
                .and(TeacherSpecification.hasGender(filter.getGender()))
                .and(TeacherSpecification.hasSpecialization(filter.getSpecialization()));

        // Pagination of the teachers with the specification
        Page<Teacher> teachers = teacherRepository.findAll(spec, pageable);
        
        // Map all the teachers into dto
        List<TeacherResponse> response = teachers.getContent()
                .stream()
                .map(teacherMapper::toDto)
                .toList();
        
        return PaginatedRes.<TeacherResponse>builder().
                status("success").
                data(response).
                currentPage(teachers.getNumber()).
                totalPage(teachers.getTotalPages()).
                totalItems(teachers.getTotalElements()).
                build();


    }

}

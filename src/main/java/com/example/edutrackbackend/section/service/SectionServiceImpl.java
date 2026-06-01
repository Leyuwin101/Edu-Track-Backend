package com.example.edutrackbackend.section.service;

import com.example.edutrackbackend.course.model.Course;
import com.example.edutrackbackend.course.validation.CourseValidator;
import com.example.edutrackbackend.section.dto.SectionRequest;
import com.example.edutrackbackend.section.dto.SectionResponse;
import com.example.edutrackbackend.section.mapper.SectionMapper;
import com.example.edutrackbackend.section.model.Section;
import com.example.edutrackbackend.section.repository.SectionRepository;
import com.example.edutrackbackend.section.validation.SectionValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;
    private final SectionValidator sectionValidator;
    private final CourseValidator courseValidator;

    /**
     * Create section
     *
     * Process:
     * - Use validator to check if the course exists
     * - Map the request dto to section entity(db)
     * - Save the section to database
     *
     * @param request section registration data
     * @return saved section response
     */
    @Override
    public SectionResponse createSection(SectionRequest request) {

        log.info("[SECTION][CREATE] Start section={}", request.getSectionName());

        Course course = courseValidator.validateCourseExists(request.getCourseId());

        Section section = sectionMapper.toEntity(request, course);

        Section saved = sectionRepository.save(section);

        log.info("[SECTION][CREATE] Success section={}", saved.getSectionName());

        return sectionMapper.toDto(saved);
    }

    /**
     * Update section
     *
     * Process:
     * - Use validator to check if section exists
     * - Map the section for update entity
     * - Save the updated section
     *
     * @param sectionId section id to update
     * @param request updated section registration data
     * @return update section response
     */
    @Override
    public SectionResponse updateSection(Long sectionId, SectionRequest request) {

        log.info("[SECTION][UPDATE] Start sectionId={}", sectionId);

        Section section = sectionValidator.validateSectionExists(sectionId);

        sectionMapper.updateEntity(section, request);

        Section saved = sectionRepository.save(section);

        log.info("[SECTION][UPDATE] Success sectionId={}", sectionId);

        return sectionMapper.toDto(saved);
    }

    /**
     * Delete section
     *
     * Process:
     * - Use validator to check if the section exists
     * - Delete section
     *
     * @param sectionId section id to delete
     */
    @Override
    public void deleteSection(Long sectionId) {

        log.info("[SECTION][DELETE] Start sectionId={}", sectionId);

        Section section = sectionValidator.validateSectionExists(sectionId);

        sectionRepository.delete(section);

        log.info("[SECTION][DELETE] Success sectionId={}", sectionId);
    }

    /**
     * Fetch existing section id
     *
     * Process:
     * - Validate if the section exists
     *
     * @param sectionId section id to fetch
     * @return section response
     */
    @Override
    public SectionResponse getSectionById(Long sectionId) {

        log.info("[SECTION][GET] Start sectionId={}", sectionId);

        Section section = sectionValidator.validateSectionExists(sectionId);

        log.info("[SECTION][GET] Success sectionId={}", sectionId);

        return sectionMapper.toDto(section);
    }

    /**
     * Fetch all sections
     *
     * Process:
     * - Retrieves all the sections from the database
     *
     * @return List of sections
     */
    @Override
    public List<SectionResponse> getAllSections() {

        log.info("[SECTION][GET_ALL] Fetching all sections");

        List<Section> sections = sectionRepository.findAll();

        return sections.stream()
                .map(sectionMapper::toDto)
                .toList();
    }




}

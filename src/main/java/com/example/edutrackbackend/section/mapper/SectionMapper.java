package com.example.edutrackbackend.section.mapper;

import com.example.edutrackbackend.course.mapper.CourseMapper;
import com.example.edutrackbackend.course.model.Course;
import com.example.edutrackbackend.section.dto.SectionRequest;
import com.example.edutrackbackend.section.dto.SectionResponse;
import com.example.edutrackbackend.section.model.Section;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = CourseMapper.class)
public interface SectionMapper {

    /**
     * Converts SectionRequest + Course into Section entity.
     * Used when creating a new Section record with an already-resolved Course.
     */
    @Mapping(target = "course", source = "course")
    Section toEntity(SectionRequest request, Course course );

    /**
     * Converts Section entity into SectionResponse DTO.
     * Maps nested Course entity into CourseDTO using CourseMapper#toCourseDTO.
     */
    @Mapping(target = "course", source = "course", qualifiedByName = "toCourseDTO")
    SectionResponse toDto(Section section);

    /**
     * Updates an existing Section entity with values from SectionRequest.
     * Ignores system-managed fields and relationships.
     */
    @Mapping(target = "sectionId", ignore = true)
    @Mapping(target = "course", ignore = true)
    void updateEntity(@MappingTarget Section section, SectionRequest request);
}

package com.example.edutrackbackend.section.service;

import com.example.edutrackbackend.section.dto.SectionRequest;
import com.example.edutrackbackend.section.dto.SectionResponse;

import java.util.List;

public interface SectionService {

    SectionResponse createSection(SectionRequest request);

    SectionResponse updateSection(Long sectionId, SectionRequest request);

    void deleteSection(Long sectionId);

    SectionResponse getSectionById(Long sectionId);

    List<SectionResponse> getAllSections();
}

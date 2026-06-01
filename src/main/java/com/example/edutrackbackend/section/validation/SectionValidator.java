package com.example.edutrackbackend.section.validation;

import com.example.edutrackbackend.section.exception.SectionNotFoundException;
import com.example.edutrackbackend.section.model.Section;
import com.example.edutrackbackend.section.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SectionValidator {

    private final SectionRepository sectionRepository;

    // Section Validator if exists
    public Section validateSectionExists(Long sectionId) {

        return sectionRepository.findById(sectionId)
                .orElseThrow(() -> new SectionNotFoundException("Section not found: " + sectionId));
    }
}

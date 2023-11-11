package com.mediscreen.predictor.service;

import com.mediscreen.predictor.model.Notes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class KeywordSearchService {
    @Autowired
    private NotesService notesService;
    @Autowired
    private DemographicsService demographicsService;

    public int countKeywordsForPatient(int patId) {
        List<Notes> patientNotes = notesService.getNotesByPatientId(patId);
        List<String> keywords = Arrays.asList("cancer", "diabetes", "None", "Hemoglobin A1C", "Microalbumin",
                "Body Height", "Body Weight", "Smoker", "Abnormal", "Cholesterol",
                "Dizziness", "Relapse", "Reaction", "Antibodies");

        List<String> lowerCaseKeywords = keywords.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        int count = 0;

        for (Notes note : patientNotes) {
            String noteContentLower = note.getNote().toLowerCase();
            for (String keyword : lowerCaseKeywords) {
                if (noteContentLower.contains(keyword)) {
                    count++;
                }
            }
        }
        log.info("Logging the count: {}", count);
        return count;
    }


    public List<String> getKeywordsFromNotes(List<Notes> notes) {
        List<String> keywords = Arrays.asList("cancer", "diabetes", "None", "Hemoglobin A1C", "Microalbumin", "Body Height", "Body Weight", "Smoker", "Abnormal", "Cholesterol", "Dizziness", "Relapse", "Reaction", "Antibodies");
        return keywords;
    }
}

package com.mediscreen.predictor.service;

import com.mediscreen.predictor.model.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KeywordSearchService {
    @Autowired
    private NotesService notesService;

    public Map<String, Long> countKeywordsForPatient(int patId) {
        List<Notes> patientNotes = notesService.getNotesByPatientId(patId);
        Map<String, Long> keywordCounts = new HashMap<>();
        List<String> keywords = Arrays.asList("cancer", "diabetes", "None");

        for (String keyword : keywords) {
            keywordCounts.put(keyword, patientNotes.stream()
                    .filter(note -> note.getNote().toLowerCase().contains(keyword))
                    .count());
        }
        return keywordCounts;
    }
}
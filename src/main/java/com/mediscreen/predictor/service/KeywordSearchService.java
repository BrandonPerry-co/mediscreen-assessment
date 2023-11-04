package com.mediscreen.predictor.service;

import com.mediscreen.predictor.model.Notes;
import com.mediscreen.predictor.model.Patient;
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
    @Autowired
    private DemographicsService demographicsService;

//    public Map<String, Long> countKeywordsForPatient(int patId) {
//        List<Notes> patientNotes = notesService.getNotesByPatientId(patId);
//        Map<String, Long> keywordCounts = new HashMap<>();
//        List<String> keywords = Arrays.asList("cancer", "diabetes", "None", "Hemoglobin A1C", "Microalbumin", "Body Height", "Body Weight", "Smoker", "Abnormal", "Cholesterol", "Dizziness", "Relapse", "Reaction", "Antibodies");
//
//        for (String keyword : keywords) {
//            keywordCounts.put(keyword, patientNotes.stream()
//                    .filter(note -> note.getNote().toLowerCase().contains(keyword))
//                    .count());
//        }
//        return keywordCounts;
//    }
//public Map<String, Long> countKeywordsForPatient(int patId) {
//    List<Notes> patientNotes = notesService.getNotesByPatientId(patId);
//    Map<String, Long> keywordCounts = new HashMap<>();
//    List<String> keywords = Arrays.asList("cancer", "diabetes", "None", "Hemoglobin A1C", "Microalbumin", "Body Height", "Body Weight", "Smoker", "Abnormal", "Cholesterol", "Dizziness", "Relapse", "Reaction", "Antibodies");
//
//    for (String keyword : keywords) {
//        long count = patientNotes.stream()
//                .filter(note -> note.getNote().toLowerCase().contains(keyword.toLowerCase()))
//                .count();
//        keywordCounts.put(keyword, count);
//    }
//    return keywordCounts;
//}

//    public Map<String, Long> countKeywordsForPatient(int patId) {
//        List<Notes> patientNotes = notesService.getNotesByPatientId(patId);
//        Map<String, Long> keywordCounts = new HashMap<>();
//        List<String> keywords = Arrays.asList("cancer", "diabetes", "None", "Hemoglobin A1C", "Microalbumin", "Body Height", "Body Weight", "Smoker", "Abnormal", "Cholesterol", "Dizziness", "Relapse", "Reaction", "Antibodies");
//
//        for (String keyword : keywords) {
//            long count = patientNotes.stream()
//                    .filter(note -> note.getNote().toLowerCase().contains(keyword.toLowerCase()))
//                    .count();
//            keywordCounts.put(keyword, count);
//        }
//        return keywordCounts;
//    }

    public Map<String, Long> countKeywordsForPatient(int patId) {
        List<Notes> patientNotes = notesService.getNotesByPatientId(patId);
        Map<String, Long> keywordCounts = new HashMap<>();
        List<String> keywords = Arrays.asList("cancer", "diabetes", "None", "Hemoglobin A1C", "Microalbumin", "Body Height", "Body Weight", "Smoker", "Abnormal", "Cholesterol", "Dizziness", "Relapse", "Reaction", "Antibodies");

        for (String keyword : keywords) {
            long count = patientNotes.stream()
                    .flatMap(note -> Arrays.stream(note.getNote().toLowerCase().split("\\s+")))
                    .filter(word -> word.equals(keyword.toLowerCase()))
                    .count();
            keywordCounts.put(keyword, count);
        }
        return keywordCounts;
    }


//    public String determineDiabetesRisk(int patId, int age, String sex) {
//        List<Notes> patientNotes = notesService.getNotesByPatientId(patId);
//        Patient patient = demographicsService.getPatient(patId);
//        List<String> triggerTerms = Arrays.asList("cancer", "diabetes", "None", "Hemoglobin A1C", "Microalbumin", "Body Height", "Body Weight", "Smoker", "Abnormal", "Cholesterol", "Dizziness", "Relapse", "Reaction", "Antibodies");
//
//        long triggerTermCount = patientNotes.stream()
//                .flatMap(note -> Arrays.stream(note.getNote().toLowerCase().split("\\s+")))
//                .filter(word -> triggerTerms.contains(word))
//                .count();
//
//        if (triggerTermCount == 0) {
//            return "None";
//        } else if (triggerTermCount >= 2 && age > 30) {
//            return "Borderline";
//        } else if (age < 30) {
//            int requiredTriggers = (sex.equalsIgnoreCase("male") && age < 30) ? 3 : 4;
//            if (triggerTermCount >= requiredTriggers) {
//                return "In danger";
//            } else {
//                return "Borderline";
//            }
//        } else {
//            int requiredTriggers = (sex.equalsIgnoreCase("male") && age < 30) ? 5 : 7;
//            if (triggerTermCount >= requiredTriggers) {
//                return "Early Onset";
//            } else {
//                return "In danger";
//            }
//        }
//    }

    public List<String> getKeywordsFromNotes(List<Notes> notes) {
        List<String> keywords = Arrays.asList("cancer", "diabetes", "None", "Hemoglobin A1C", "Microalbumin", "Body Height", "Body Weight", "Smoker", "Abnormal", "Cholesterol", "Dizziness", "Relapse", "Reaction", "Antibodies");
        return keywords;
    }

}

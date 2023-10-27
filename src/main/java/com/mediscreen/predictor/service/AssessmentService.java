package com.mediscreen.predictor.service;

import com.mediscreen.predictor.model.Notes;
import com.mediscreen.predictor.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Determine the assessment of patients
 */
@Service
public class AssessmentService {

    @Autowired
    private DemographicsService demographicsService;
    @Autowired
    private NotesService notesService;
    @Autowired
    private KeywordSearchService keywordSearchService;

    public String assessPatient(Integer id) {
        String result = "Patient: {firstName} {lastName} (age {age}) diabetes assessment is: {assessment}";
        String firstName = demographicsService.getPatient(id).getGiven(); // Get firstname from the demographic service
        String lastName = demographicsService.getPatient(id).getFamily(); // Get from the demographic service
        String age = demographicsService.getPatient(id).getDob(); // Get from the demographic service
       // String assessment = keywordSearchService.countKeywordsForPatient(patId).toString(); //To be determined below
// TODO: 10/15/2023 Determine the assessment by using information from the other services
        // Get patient information from demographic service using patId
        Patient patient = demographicsService.getPatient(id);
        // Get notes from notes service using patId
        List<Notes> notes = notesService.getNotesByPatientId(id);
        // Get keywords from keywords service using patient notes
        List<String> keywords = keywordSearchService.getKeywordsFromNotes(notes);
        // Determine the assessment based on the keywords
//        String assessment = determineAssessment(keywords);
        // Replace placeholders in the result string
        result = result.replace("{firstName}", patient.getGiven());
        result = result.replace("{lastName}", patient.getFamily());
        // TODO: 10/17/2023 add getter in patient object that returns age
        result = result.replace("{age}", patient.getAge());
        String assessment = determineAssessment(keywordSearchService.getKeywordsFromNotes(notes));
        result = result.replace("{assessment}", assessment);
        return result;
    }

    private String determineAssessment(List<String> keywords) {
        NotesService notesService = new NotesService();
        List<Notes> patientNotes = notesService.getNotesByPatientId(1);
        String assessment = "None";
//        if (patientNotes.stream().anyMatch(note -> note.getNote().contains("None"))) {
//            assessment = "In Danger";
//        }
        if (patientNotes.stream().anyMatch(note -> {System.out.println(note.toString());return false;})) {
            assessment = "In Danger";
        }
        return assessment;
    }
}

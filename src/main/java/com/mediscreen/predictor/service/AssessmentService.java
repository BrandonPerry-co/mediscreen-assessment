package com.mediscreen.predictor.service;

import com.mediscreen.predictor.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String assessPatient(Integer patId) {
        String result = "Patient: {firstName} {lastName} (age {age}) diabetes assessment is: {assessment}";
        String firstName = "Unknown"; // Get firstname from the demographic service
        String lastName = "Unknown"; // Get from the demographic service
        String age = "Unknown"; // Get from the demographic service
        String assessment = "Unknown"; //To be determined below
// TODO: 10/15/2023 Determine the assessment by using information from the other services
        // Get patient information from demographic service using patId
        Patient patient = demographicsService.getPatient(patId);
        // Get notes from notes service using patId
        // Get keywords from keywords service using patient notes
        result = result.replace("{firstName}", patient.getGiven());
        result = result.replace("{lastName}", patient.getFamily());
        // TODO: 10/17/2023 add getter in patient object that returns age 
        result = result.replace("{age}", patient.getDob());
        result = result.replace("{assessment}", assessment);
        return result;
    }
}

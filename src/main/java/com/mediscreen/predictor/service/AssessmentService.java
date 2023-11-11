package com.mediscreen.predictor.service;

import com.mediscreen.predictor.model.Patient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Determine the assessment of patients
 */
@Slf4j
@Service
public class AssessmentService {

    @Autowired
    private DemographicsService demographicsService;
    @Autowired
    private NotesService notesService;
    @Autowired
    private KeywordSearchService keywordSearchService;

    public String determineDiabetesRisk(int patId) {
        Patient patient = demographicsService.getPatientDemo(patId);
        int triggerTerms = keywordSearchService.countKeywordsForPatient(patId);
        int age = patient.getAge();
        String sex = patient.getSex();
        String patientName = patient.getGiven() + " " + patient.getFamily();
        String assessmentResult = "None";

        if ((age > 30 && triggerTerms >= 8) ||
                (sex.equalsIgnoreCase("female") && age < 30 && triggerTerms >= 7) ||
                (sex.equalsIgnoreCase("male") && age < 30 && triggerTerms >= 5)
        ) {
            assessmentResult = "Early onset";
        } else if ((age > 30 && triggerTerms == 6) ||
                (sex.equalsIgnoreCase("female") && age < 30 && triggerTerms >= 4 && triggerTerms <= 6) ||
                (sex.equalsIgnoreCase("male") && age < 30 && triggerTerms >= 3 && triggerTerms <= 4)
        ) {
            assessmentResult = "In Danger";

        } else if (age > 30 && triggerTerms == 2
        ) {
            assessmentResult = "Borderline";
        }
        log.info("Logging the assessment result: {}", assessmentResult);
        return String.format("Patient: %s (age %d) diabetes assessment is: %s", patientName, age, assessmentResult);
    }
}

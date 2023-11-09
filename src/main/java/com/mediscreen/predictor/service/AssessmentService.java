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

    public String determineDiabetesRisk(int patId) {
//        List<Notes> patientNotes = notesService.getNotesByPatientId(patId);
        Patient patient = demographicsService.getPatient(patId);
        int triggerTerms = keywordSearchService.countKeywordsForPatient(patId);
        int age = patient.getAge();
        String sex = patient.getSex();
        String patientName = patient.getGiven() + " " + patient.getFamily();
        String assessmentResult = "None";


//○ None - patient has no doctor’s notes containing any of the trigger
//terminology. *DONE*
//○ Borderline - patient has a reference to two triggers, and is over 30 years of
//age. *DONE*
//○ In danger - depends on patient’s age and sex. If under 30, and male, then
//three trigger terms.
//○ If under 30 and female, four trigger terms. If either over 30, then six.
//○ Early Onset - again, depends on age and sex. If under 30, and male, then
//five trigger terms.
//○ If under 30 and female, seven trigger terms. If over 30, then eight or more.


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
        return String.format("Patient: %s (age %d) diabetes assessment is: %s", patientName, age, assessmentResult);
    }
}

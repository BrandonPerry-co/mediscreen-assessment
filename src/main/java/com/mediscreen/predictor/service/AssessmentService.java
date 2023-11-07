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

//    public String assessPatient(Integer id) {
//        String result = "Patient: {firstName} {lastName} (age {age}) diabetes assessment is: {assessment}";
//        String firstName = demographicsService.getPatient(id).getGiven(); // Get firstname from the demographic service
//        String lastName = demographicsService.getPatient(id).getFamily(); // Get from the demographic service
//        String age = demographicsService.getPatient(id).getDob(); // Get from the demographic service
//        // String assessment = keywordSearchService.countKeywordsForPatient(patId).toString(); //To be determined below
//// TODO: 10/15/2023 Determine the assessment by using information from the other services
//        // Get patient information from demographic service using patId
//        Patient patient = demographicsService.getPatient(id);
//        // Get notes from notes service using patId
//        List<Notes> notes = notesService.getNotesByPatientId(id);
//        // Get keywords from keywords service using patient notes
//        List<String> keywords = keywordSearchService.getKeywordsFromNotes(notes);
//        // Determine the assessment based on the keywords
////        String assessment = determineAssessment(keywords);
//        // Replace placeholders in the result string
//        result = result.replace("{firstName}", patient.getGiven());
//        result = result.replace("{lastName}", patient.getFamily());
//        // TODO: 10/17/2023 add getter in patient object that returns age
//        result = result.replace("{age}", patient.getAge());
//        String assessment = determineDiabetesRisk (id);
//        result = result.replace("{assessment}", assessment);
//        return result;
//    }

//    private String determineAssessment(List<String> keywords) {
//        List<Notes> patientNotes = notesService.getNotesByPatientId(1);
////        String assessment = "None";
//        String assessment = keywordSearchService.countKeywordsForPatient(1).toString();
//        // TODO: 10/28/2023 determine if how many trigger terms are in the notes
//        if (patientNotes.stream().anyMatch(note -> note.getNote().contains("None"))) {
//            assessment = "In Danger";
//        }
//        return assessment;
//    }


//    public String determineDiabetesRisk(int patId) {
//        List<Notes> patientNotes = notesService.getNotesByPatientId(patId);
//        Patient patient = demographicsService.getPatient(patId);
//        int triggerTerms = keywordSearchService.countKeywordsForPatient(patId);
//        int age = patient.getAge();
//        String sex = patient.getSex();
//        String patientName = patient.getGiven() + " " + patient.getFamily();
//        String assessmentResult;
//
//        switch (triggerTerms) {
//            case 0:
//                assessmentResult = "None";
//                break;
//            case 1:
//            case 2:
//                if (age > 30) {
//                    assessmentResult = "Borderline";
//                } else {
//                    int requiredTriggers = (patient.getSex().equalsIgnoreCase("male") && age < 30) ? 3 : 4;
//                    assessmentResult = (triggerTerms >= requiredTriggers) ? "In danger" : "Borderline";
//                }
//                break;
//            default:
//                int requiredTriggers = (patient.getSex().equalsIgnoreCase("male") && age < 30) ? 5 : 7;
//                assessmentResult = (triggerTerms >= requiredTriggers) ? "Early Onset" : "In danger";
//                break;
//        }
//
//        return String.format("Patient: %s (age %d) diabetes assessment is: %s", patientName, age, assessmentResult);
//    }

    public String determineDiabetesRisk(int patId) {
//        List<Notes> patientNotes = notesService.getNotesByPatientId(patId);
        Patient patient = demographicsService.getPatient(patId);
        int triggerTerms = keywordSearchService.countKeywordsForPatient(patId);
        int age = patient.getAge();
        String sex = patient.getSex();
        String patientName = patient.getGiven() + " " + patient.getFamily();
        String assessmentResult;
        if (triggerTerms == 0) {
            assessmentResult = "None";
        } else if (triggerTerms >= 2 && age > 30) {
            assessmentResult = "Borderline";
        } else if (age < 30) {
            int requiredTriggers = (sex.equalsIgnoreCase("male") && age < 30) ? 3 : 4;
            if (triggerTerms >= requiredTriggers) {
                assessmentResult = "In Here";
//                return String.format("Patient: Test TestNone (age %d) diabetes assessmentIn danger", age);
                // return String.format("Today's weather is %s, with a temperature of %s degrees %s",feelsLike, temperature, unit);
            } else {
                assessmentResult = "Borderline";
            }
        } else {
            int requiredTriggers = (sex.equalsIgnoreCase("male") && age < 30) ? 5 : 7;
            if (triggerTerms >= requiredTriggers) {
                assessmentResult = "Early Onset";
            } else {
                assessmentResult = "In danger 2";
//                return String.format("Patient: Test TestNone (age %d) diabetes assessmentIn danger", age);
            }
        }
        //return String.format("Patient: (%s) (%s) (age %d) diabetes assessment %s",age);
        return String.format("Patient: %s (age %d) diabetes assessment is: %s", patientName, age, assessmentResult);
    }


}

package com.mediscreen.predictor.controller;


import com.mediscreen.predictor.model.Patient;
import com.mediscreen.predictor.service.AssessmentService;
import com.mediscreen.predictor.service.DemographicsService;
import com.mediscreen.predictor.service.KeywordSearchService;
import com.mediscreen.predictor.service.NotesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/assess")

public class AssessmentController {

    @Autowired
    private DemographicsService demographicsService;
    @Autowired
    private AssessmentService assessmentService;

    @Autowired
    private KeywordSearchService keywordSearchService;

    @Autowired
    private NotesService notesService;

    @GetMapping("/id")
    public ResponseEntity<String> assessPatient(@RequestParam Integer patId) {
        try {
            // Fetching the diabetes risk assessment for the patient
            String assessmentResult = assessmentService.determineDiabetesRisk(patId);
            log.info("Diabetes risk assessment for patient {}: {}", patId, assessmentResult);

            return new ResponseEntity<>(assessmentResult, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error assessing patient: {}", e.getMessage());
            // You can customize the error response based on the exception type and message
            return new ResponseEntity<>("Error in assessing patient please verify the patient's information is correct!.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/family")
    public ResponseEntity<Patient> assessPatients(@RequestParam String family) throws Exception {
        // TODO: 11/10/2023 Get the patient Demographics by ID DONE
        Optional<Patient> onePatient = demographicsService.getPatientByFamilyName(family);
        if (onePatient == null) {
            throw new Exception("Patient not found!");
        }
        log.info("Assessing all patients: {}", family);
        System.out.println(onePatient);


        String patientId = demographicsService.getPatientIdByFamilyName(family);
        log.info("Assessing all: {}", family);
        System.out.println(patientId);


        return new ResponseEntity<>(onePatient.get(), HttpStatus.OK);
    }
}

package com.mediscreen.predictor.controller;


import com.mediscreen.predictor.service.AssessmentService;
import com.mediscreen.predictor.service.DemographicsService;
import com.mediscreen.predictor.service.KeywordSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assess")

public class AssessmentController {

    @Autowired
    private DemographicsService demographicsService;
    @Autowired
    private AssessmentService assessmentService;

    @Autowired
    private KeywordSearchService keywordSearchService;

    @GetMapping("/id")
    public ResponseEntity<String> assessPatient(@RequestParam Integer patId) {



        if (!demographicsService.patientExists(patId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // TODO: 10/15/2023 Determine the assessment using the assessment service

        var result = assessmentService.determineDiabetesRisk(patId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

package com.mediscreen.predictor.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class AssessmentServiceTest {
    @Autowired
    private AssessmentService assessmentService;

    @Test
    public void testAssessPatient() {
        // TODO: 10/15/2023 To code
        Integer patId = 1;
        String expected = "Patient: Test TestNone (age 52) diabetes assessment is: None";
        String result = assessmentService.assessPatient(patId);
        assertThat(result).isEqualTo(expected);
    }
}
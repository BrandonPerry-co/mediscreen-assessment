package com.mediscreen.predictor.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Determine the assessment of patients
 */
@Service
public class AssessmentService {

    private final Map<Integer, String[]> patientDemoData = Map.of(
            1, new String[]{"Test", "TestNone", "52", "None"}
    );
    public String assessPatient(Integer patId) {
        String result = "Patient: {firstName} {lastName} (age {age}) diabetes assessment is: {assessment}";
        // TODO: 10/15/2023 Replace hard coded values
        String[] data = patientDemoData.getOrDefault(patId, new String[]{"Unknown", "Unknown", "Unknown", "Unknown"});
        String firstName = data[0];
        String lastName = data[1];
        String age = data[2];
        String assessment = data[3];
// TODO: 10/15/2023 Determine the assessment by using information from the other services
        // Get patient information from demographic service using patId

        // Get notes from notes service using patId
        // Get keywords from keywords service using patient notes
        result = result.replace("{firstName}", firstName);
        result = result.replace("{lastName}", lastName);
        result = result.replace("{age}", age);
        result = result.replace("{assessment}", assessment);
        return result;
    }
}

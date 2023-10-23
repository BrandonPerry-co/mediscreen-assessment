package com.mediscreen.predictor.service;

import com.mediscreen.predictor.model.Notes;
import com.mediscreen.predictor.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class DemographicsService {
    @Autowired
    private RestTemplate restTemplate;
    //    private final String MED_API_URL = "http://localhost:8081/patient/findAll";
    private final String NOTES_BASE_URL = "http://localhost:8082/patHistory";
    private final String MED_BASE_URL = "http://localhost:8081/patient/findAll";

    public List<Patient> getAllPatients() {
        ResponseEntity<List<Patient>> response = restTemplate.exchange(
                MED_BASE_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Patient>>() {
                }
        );
        return response.getBody();
    }

    public boolean patientExists(int id) {
        List<Patient> allPatients = getAllPatients();
        return allPatients.stream().anyMatch(patient -> Objects.equals(patient.getId(), id));
    }

    public Patient getPatient(int id) {
        // TODO: 10/17/2023 To code
        List<Patient> patients = getAllPatients();
        return patients.stream().filter(patient -> Objects.equals(patient.getId(), id)).findFirst().orElse(null);
    }
}

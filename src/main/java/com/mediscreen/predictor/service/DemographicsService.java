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

    public boolean patientExists(Integer id) {
        List<Patient> allPatients = getAllPatients();
        return allPatients.stream().anyMatch(patient -> patient.getId().equals(id));
    }

}

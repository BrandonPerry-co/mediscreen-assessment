package com.mediscreen.predictor.service;

import com.mediscreen.predictor.model.Patient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class DemographicsService {
    @Autowired
    private RestTemplate restTemplate;
    private final String NOTES_BASE_URL = "http://localhost:8082/patHistory";
    private final String MED_BASE_URL = "http://localhost:8081/patient/findAll";

    private final String MED_API_URL = "http://localhost:8081/patient/{id}";

    //Getting all patients
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

    // Getting patient by id
    public Patient getPatientFromDB(int id) {
        ResponseEntity<Patient> response = restTemplate.exchange(
                MED_API_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Patient>() {
                },
                id);
        return response.getBody();
    }


    // Getting patient by family name
    public Optional<Patient> getPatientByFamilyName(String family) {
        ResponseEntity<List<Patient>> response = restTemplate.exchange(
                MED_BASE_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Patient>>() {
                }
        );

        List<Patient> allPatients = response.getBody();

        return allPatients.stream()
                .filter(patient -> patient.getFamily().equalsIgnoreCase(family))
                .findFirst();
    }

    // Getting patient by family name
    public String getPatientIdByFamilyName(String familyName) {
        Optional<Patient> patient = getPatientByFamilyName(familyName);

        // Check if a patient is found
        if (patient.isPresent()) {
            return patient.get().getId();
        } else {
            log.error("No patient found with family name: {}", familyName);
            throw new IllegalStateException("No patient found with family name: " + familyName);
        }
    }

    public Patient getPatientDemo(int id) {
        log.info("Assessing patient: {}", id);
        Patient onePatient = getPatientFromDB(id);
        log.info("Logging the patient: {}", onePatient);
        return onePatient;
    }

    public boolean patientExists(int id) {
        log.info("Assessing patient: {}", id);
        List<Patient> allPatients = getAllPatients();
        log.info("Logging all the patient: {}", allPatients);
        log.info("Logging all the patient: {}", allPatients.stream().anyMatch(patient -> Objects.equals(patient.getId(), id)));
        return allPatients.stream().anyMatch(patient -> Objects.equals(patient.getId(), id));
    }


    public Patient getPatient(int id) {
        // TODO: 10/17/2023 To code
        List<Patient> patients = getAllPatients();
        return patients.stream().filter(patient -> Objects.equals(patient.getId(), id)).findFirst().orElse(null);
    }
}

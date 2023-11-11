package com.mediscreen.predictor.service;

import com.mediscreen.predictor.model.Notes;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NotesService {
    private RestTemplate restTemplate = new RestTemplate();
    private final String NOTES_API_URL = "http://localhost:8082/patHistory";

    public List<Notes> getNotesByPatientId(int patId) {
        ResponseEntity<List<Notes>> response = restTemplate.exchange(
                NOTES_API_URL + "/" + patId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Notes>>() {
                }
        );
        return response.getBody();
    }
}
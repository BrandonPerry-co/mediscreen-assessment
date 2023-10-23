package com.mediscreen.predictor.service;

import com.mediscreen.predictor.model.Notes;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class NotesService {
    private RestTemplate restTemplate = new RestTemplate();
    private final String NOTES_API_URL = "http://localhost:8082/patHistory";

    public List<Notes> getNotesByPatientId(int patId) {
        return restTemplate.getForObject(NOTES_API_URL + "/" + patId, List.class);
    }

//    public Notes getNotesById(int patId, String id) {
//        return restTemplate.getForObject(NOTES_API_URL + "/" + id, Notes.class);
//    }
}

package com.mediscreen.predictor.service;

import com.mediscreen.predictor.model.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class KeywordSearchServiceTest {

    @Mock
    private NotesService notesServiceMock;
    @Mock
    private NotesService notesService;

    @InjectMocks
    private KeywordSearchService keywordSearchService;

    private List<Notes> mockNotes;

    @BeforeEach
    public void setUp() {
        mockNotes = Arrays.asList(
                new Notes("1", 2, "Patient has diabetes."),
                new Notes("2", 2, "No signs of cancer."),
                new Notes("3", 2, "Regular checkup."),
                new Notes("4", 2, "Cholesterol levels are abnormal.")
        );
    }

    @Test
    public void testCountKeywordsForPatient() {
        when(notesService.getNotesByPatientId(2)).thenReturn(mockNotes);
        int keywordCount = keywordSearchService.countKeywordsForPatient(2);
        assertEquals(4, keywordCount, "The keyword count should be 4");
    }

    @Test
    public void testKeywordSearch() {
        int id = 1;
        List<Notes> testNotes = List.of(Notes.builder().patId(id).note("Patient: has red blood None None none Dizziness Dizziness smoker A1c").build());
        int expected = 3;
        when(notesService.getNotesByPatientId(id)).thenReturn(testNotes);
        int result = keywordSearchService.countKeywordsForPatient(id);
        assertThat(result).isEqualTo(expected);
    }

}

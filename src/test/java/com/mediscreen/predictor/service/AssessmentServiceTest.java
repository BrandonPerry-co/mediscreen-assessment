package com.mediscreen.predictor.service;

import com.mediscreen.predictor.model.Notes;
import com.mediscreen.predictor.model.Patient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssessmentServiceTest {

    @Mock
    private DemographicsService demographicsServiceMock;
    @Mock
    private NotesService notesServiceMock;
    @Mock
    private KeywordSearchService keywordSearchServiceMock;
    @InjectMocks
    private AssessmentService assessmentService;


    @Test
    public void testAssessPatient() {
        // TODO: 10/15/2023 To code
        int id = 1;
        Patient testPatient = Patient.builder().given("Test").family("TestNone").dob("1/23/1970").build();
        List<Notes> testNotes = List.of(Notes.builder().patId(id).note("Patient: has red blood None None Body Weight.").build());
        String expected = "Patient: Test TestNone (age 53) diabetes assessment is: In Danger";
        when(demographicsServiceMock.getPatient(id)).thenReturn(testPatient);
        when(notesServiceMock.getNotesByPatientId(id)).thenReturn(testNotes);
        String result = assessmentService.assessPatient(id);
        assertThat(result).isEqualTo(expected);
    }

}
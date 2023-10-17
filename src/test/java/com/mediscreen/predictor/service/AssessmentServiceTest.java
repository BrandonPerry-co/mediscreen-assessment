package com.mediscreen.predictor.service;

import com.mediscreen.predictor.model.Patient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssessmentServiceTest {
    @InjectMocks
    private AssessmentService assessmentService;
    @Mock
    private DemographicsService demographicsServiceMock;
    @Mock
    private NotesService notesServiceMock;
    @Mock
    private KeywordSearchService keywordSearchServiceMock;


    @Test
    public void testAssessPatient() {
        // TODO: 10/15/2023 To code
        Integer patId = 1;
        Patient testPatient = Patient.builder().given("Test").family("TestNone").dob("1/23/1990").build();
        String expected = "Patient: Test TestNone (age 52) diabetes assessment is: None";
        when(demographicsServiceMock.getPatient(patId)).thenReturn(testPatient);
        String result = assessmentService.assessPatient(patId);
        assertThat(result).isEqualTo(expected);
    }
}
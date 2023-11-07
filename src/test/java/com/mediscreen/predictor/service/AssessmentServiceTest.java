package com.mediscreen.predictor.service;

import com.mediscreen.predictor.model.Notes;
import com.mediscreen.predictor.model.Patient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
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


//    @Test
//    public void testAssessPatient() {
//        // TODO: 10/15/2023 To code
//        int id = 1;
//        Patient testPatient = Patient.builder().given("Test").family("TestNone").dob("1/23/1970").build();
//        List<Notes> testNotes = List.of(Notes.builder().patId(id).note("Patient: has red blood None None Body Weight.").build());
//        String expected = "Patient: Test TestNone (age 53) diabetes assessment is: None";
//        when(demographicsServiceMock.getPatient(id)).thenReturn(testPatient);
//        when(notesServiceMock.getNotesByPatientId(id)).thenReturn(testNotes);
//        String result = assessmentService.assessPatient(id);
//        assertThat(result).isEqualTo(expected);
//    }

    @Test
    public void testAssessmentOfPatient() {
        int id = 1;
        Patient testPatient = Patient.builder().given("Test").family("TestNone").dob("1/23/1970").sex("male").build();
        List<Notes> testNotes = List.of(Notes.builder().patId(id).note("Patient: has red blood and is healthy risk assessment returned none.").build());
        String expected = "Patient: Test TestNone (age 53) diabetes assessment is: None";
        when(demographicsServiceMock.getPatient(id)).thenReturn(testPatient);
        when(keywordSearchServiceMock.countKeywordsForPatient(1)).thenReturn(testNotes.size());
        String result = assessmentService.determineDiabetesRisk(id);
        assertThat(result).isEqualTo(expected);
        System.out.println(result);

    }
//@Test
//    public void testAssessmentOfPatient() {
//        int id = 1;
//        Patient testPatient = Patient.builder().given("Test").family("TestNone").dob("1/23/1970").sex("male").build();
//
//        // Test case where no keywords are found in patient's notes
//        List<Notes> emptyNotes = Collections.emptyList();
//
//        // Test case where 2 keywords are found in patient's notes
//        List<Notes> twoKeywordsNotes = List.of(
//                Notes.builder().patId(id).note("Patient: has red blood None None none none none alc.").build(),
//                Notes.builder().patId(id).note("Patient: has diabetes symptoms None None None none none none alc").build()
//        );
//
//        String expectedNone = "Patient: Test TestNone (age 53) diabetes assessment is: None";
//        String expectedInDanger = "Patient: Test TestNone (age 53) diabetes assessment is: In danger";
//
//        when(demographicsServiceMock.getPatient(id)).thenReturn(testPatient);
//
//        // Mock the keywordSearchService to handle different scenarios
//        when(keywordSearchServiceMock.countKeywordsForPatient(1)).thenReturn(emptyNotes.size());
//        String resultNone = assessmentService.determineDiabetesRisk(id);
//        assertThat(resultNone).isEqualTo(expectedNone);
//        System.out.println(resultNone);
//
//        when(keywordSearchServiceMock.countKeywordsForPatient(1)).thenReturn(twoKeywordsNotes.size());
//        String resultInDanger = assessmentService.determineDiabetesRisk(id);
//        assertThat(resultInDanger).isEqualTo(expectedInDanger);
//        System.out.println(resultInDanger);
//    }

}
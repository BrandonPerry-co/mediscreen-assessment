package com.mediscreen.predictor.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
//        int id = 1;
//        Patient testPatient = Patient.builder().given("Test").family("TestNone").dob("1/23/1970").sex("male").build();
//        List<Notes> testNotes = List.of(Notes.builder().patId(id).note("Patient: has red blood None None Body Weight.").build());
//        String expected = "Patient: Test TestNone (age 53) diabetes assessment is: None";
//        when(demographicsServiceMock.getPatient(id)).thenReturn(testPatient);
//        when(notesServiceMock.getNotesByPatientId(id)).thenReturn(testNotes);
//
//        when(keywordSearchServiceMock.countKeywordsForPatient(id)).thenReturn(1);
//        String result = assessmentService.determineDiabetesRisk(id);
//        assertThat(result).isEqualTo(expected);
//    }
//
//    @Test
//    public void testAssessmentFemalePatient6TriggerWordsOver30() {
//        int id = 1;
//        Patient testPatient = Patient.builder().given("Test").family("TestNone").dob("1/23/1970").sex("male").build();
//        String expected = "Patient: Test TestNone (age 53) diabetes assessment is: In Danger";
//        when(demographicsServiceMock.getPatient(id)).thenReturn(testPatient);
//        when(keywordSearchServiceMock.countKeywordsForPatient(id)).thenReturn(6);
//        String result = assessmentService.determineDiabetesRisk(id);
//        assertThat(result).isEqualTo(expected);
//        System.out.println(result);
//    }
//
//    @Test
//    public void testAssessmentMalePatient2TriggerWordsOver30() {
//        int id = 1;
//        Patient testPatient = Patient.builder().given("Test").family("TestNone").dob("1/23/1970").sex("male").build();
//        String expected = "Patient: Test TestNone (age 53) diabetes assessment is: Borderline";
//        when(demographicsServiceMock.getPatient(id)).thenReturn(testPatient);
//        when(keywordSearchServiceMock.countKeywordsForPatient(id)).thenReturn(2);
//        String result = assessmentService.determineDiabetesRisk(id);
//        assertThat(result).isEqualTo(expected);
//        System.out.println(result);
//    }
//
//    @Test
//    public void testAssessmentOfFemalePatientWith1TriggerWordUnder30() {
//        int id = 1;
//        Patient testPatient = Patient.builder().given("Test").family("TestNone").dob("1/23/2000").sex("female").build();
//        String expected = "Patient: Test TestNone (age 23) diabetes assessment is: None";
//        when(demographicsServiceMock.getPatient(id)).thenReturn(testPatient);
//        when(keywordSearchServiceMock.countKeywordsForPatient(id)).thenReturn(1);
//        String result = assessmentService.determineDiabetesRisk(id);
//        assertThat(result).isEqualTo(expected);
//        System.out.println(result);
//
//    }
//
//    @Test
//    public void testAssessmentOfMalePatient3TriggerWordsUnder30() {
//        int id = 1;
//        Patient testPatient = Patient.builder().given("Test").family("TestNone").dob("1/23/2000").sex("male").build();
//        //List<Notes> testNotes = List.of(Notes.builder().patId(id).note("Patient: has red blood and is healthy risk assessment returned none smoker.").build());
//        String expected = "Patient: Test TestNone (age 23) diabetes assessment is: In Danger";
//        when(demographicsServiceMock.getPatient(id)).thenReturn(testPatient);
//        when(keywordSearchServiceMock.countKeywordsForPatient(id)).thenReturn(3);
//        String result = assessmentService.determineDiabetesRisk(id);
//        assertThat(result).isEqualTo(expected);
//        System.out.println(result);
//    }

}
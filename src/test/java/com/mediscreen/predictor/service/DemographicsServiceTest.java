package com.mediscreen.predictor.service;

import com.mediscreen.predictor.model.Patient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DemographicsServiceTest {

    private DemographicsService demographicsService;
    @Mock
    private DemographicsService demographicsServiceMock;

    @Test
    void testGetAllPatients() {
        List<Patient> testPatients = List.of(Patient.builder().id("null").address("711 Neil Dr.").given("Test").family("TestNone").dob("1/23/1970").sex("male").phone("222-222-2222").build());
        String expected = "[Patient(id=null, address=711 Neil Dr., dob=1/23/1970, family=TestNone, given=Test, sex=male, phone=222-222-2222)]";
        when(demographicsServiceMock.getAllPatients()).thenReturn(testPatients);
        String result = demographicsServiceMock.getAllPatients().toString();
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testPatientGetAge() {
        int id = 1;
        Patient testPatientAge = Patient.builder().id("1").dob("1/23/1970").build();
        String expected = "1, 53";
        when(demographicsServiceMock.getPatient(id)).thenReturn(testPatientAge);
        String result = "1, " + demographicsServiceMock.getPatient(id).getAge();
        assertThat(result).isEqualTo("1, 53");
    }

    @Test
    void testPatientExists() {
        int id = 1;
        when(demographicsServiceMock.patientExists(id)).thenReturn(true);
        boolean result = demographicsServiceMock.patientExists(id);
        assertThat(result).isTrue();
    }

}
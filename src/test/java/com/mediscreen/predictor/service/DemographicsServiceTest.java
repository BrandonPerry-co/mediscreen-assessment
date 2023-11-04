package com.mediscreen.predictor.service;

import com.mediscreen.predictor.model.Patient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DemographicsServiceTest {

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
}
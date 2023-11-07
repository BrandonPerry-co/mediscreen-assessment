package com.mediscreen.predictor.controller;

import com.mediscreen.predictor.service.AssessmentService;
import com.mediscreen.predictor.service.DemographicsService;
import com.mediscreen.predictor.service.KeywordSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AssessmentController.class)
public class AssessmentControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private DemographicsService demographicsServiceMock;

    @MockBean
    private KeywordSearchService keywordSearchServiceMock;

    @MockBean
    private AssessmentService assessmentServiceMock;

    @InjectMocks
    private AssessmentController assessmentController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(assessmentController)
                .build();
    }

    @Test
    public void testAssess() throws Exception {
        var expected = "Patient: Test TestNone (age 52) diabetes assessment is: None";
        var testPatId = 1;

        when(demographicsServiceMock.patientExists(testPatId)).thenReturn(true);
        when(assessmentServiceMock.determineDiabetesRisk(testPatId)).thenReturn(expected);
        MvcResult result = mockMvc.perform(get("/assess/id").param("patId", String.valueOf(testPatId)))
                .andExpect(status().isOk()).andReturn();

        String actual = result.getResponse().getContentAsString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testPatientNotFound() throws Exception {
        var expected = status().isNotFound();
        var testPatId = 1;

        when(demographicsServiceMock.patientExists(testPatId)).thenReturn(false);
        MvcResult result = mockMvc.perform(get("/assess/id").param("patId", String.valueOf(testPatId)))
                .andExpect(expected).andReturn();
    }

}

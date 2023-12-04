package com.musalasoft.app.Controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.musalasoft.app.Models.Drone;
import com.musalasoft.app.Models.Medication;
import com.musalasoft.app.Services.DroneService;
import com.musalasoft.app.Services.MedicationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MedicationController.class)
public class MedicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicationService medicationService;

    Medication medication;

    Long medicationId;

    @BeforeEach
    void setUp(){
        medicationId    = 1l;
        medication  = new Medication("Amatem", "389YUUS", 12.0, "https://imageUrl");
    }

    @AfterEach
    void tearDown(){}




    @Test
    void testAddMedication() throws Exception{

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = mapper.writeValueAsString(medication);

        when(medicationService.addMedication(medication)).thenReturn(medication);
        this.mockMvc.perform(
                        post("/add-medication")
                                .contentType( MediaType.APPLICATION_JSON)
                                .content(requestJson))
                .andDo( print())
                .andExpect( status().isCreated());
    }

}

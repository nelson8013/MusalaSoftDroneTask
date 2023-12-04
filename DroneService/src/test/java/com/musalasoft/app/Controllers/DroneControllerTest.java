package com.musalasoft.app.Controllers;


import com.musalasoft.app.Models.Drone;
import com.musalasoft.app.Models.DroneState;
import com.musalasoft.app.Models.Medication;
import com.musalasoft.app.Services.DroneService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DroneController.class)
public class DroneControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DroneService droneService;

    Drone drone;
    Drone droneTwo;
    Drone droneThree;
    Drone droneFour;

    Medication medication;

    Long droneId;

    private DroneState state;

    List<Drone> droneList = new ArrayList<>();

    List<Drone>  availableDrones;

    private List<Medication> medications;

    @BeforeEach
    void setUp(){
        droneId    = 1l;
        drone      = new Drone("SN687903YIUO3", "Lightweight", 100.0, 100, state.IDLE);
        droneTwo   = new Drone("7823Uhjdo00-00", "Middleweight", 250.0, 100, state.IDLE);
        droneThree = new Drone("8823Uhjdo00-00", "Heavyweight", 350.0, 100, state.IDLE);
        droneFour  = new Drone("9823Uhjdo00-00", "Cruiserweight", 500.0, 100, state.IDLE);


        droneList.add(drone);
        droneList.add(droneTwo);
        droneList.add(droneThree);
        droneList.add(droneFour);

        medication = new Medication("Combantrin", "CMB45763700", 10.0, "https://combantinImgaeUrl");

        medications = Arrays.asList(
                new Medication("Amatem", "389YUUS", 12.0, "https://amartemImageUrl"),
                new Medication("Coartem", "COR47889BF", 8.0, "https://coartemImageUrl"),
                new Medication("Ciprotab", "CPT47889CF", 3.0, "https://ciprotabImageUrl")
        );

         availableDrones = Arrays.asList(
                new Drone("SN12TYOI93", "Heavyweight", 200.0, 80, state.IDLE),
                new Drone("SNRTYIU456", "Lightweight", 100.0, 70, state.IDLE)
        );
    }

    @AfterEach
    void tearDown(){}



    @Test
    void testGetDrone() throws Exception {
        when(droneService.getDrone(droneId)).thenReturn(drone);
        this.mockMvc.perform( get("/drone/1") ).andDo( print() ).andExpect( status().isOk());
    }

    @Test
    void testDrones() throws Exception {
        when(droneService.allDrones()).thenReturn(droneList);
        this.mockMvc.perform( get("/drones") ).andDo( print() ).andExpect( status().isOk());
    }


    @Test
    void testRegisterDrone() throws Exception{

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = mapper.writeValueAsString(drone);

        when(droneService.registerDrone(drone)).thenReturn(drone);
        this.mockMvc.perform(
                        post("/register-drone")
                                .contentType( MediaType.APPLICATION_JSON)
                                .content(requestJson))
                .andDo( print())
                .andExpect( status().isCreated());
    }

    @Test
    void testLoadMedication() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = mapper.writeValueAsString(medication);

        doNothing().when(droneService).loadMedication(droneId, medication);

        this.mockMvc.perform(
                        post("/load-medication/{droneId}", droneId)
                                .contentType( MediaType.APPLICATION_JSON)
                                .content(requestJson))
                .andDo( print())
                .andExpect( status().isNoContent());

        verify(droneService, times(1)).loadMedication(droneId, medication);
    }

    @Test
    void testLoadMultipleMedications() throws Exception {
        doNothing().when(droneService).loadMultipleMedications(droneId, medications);

        mockMvc.perform(post("/load-medications/{droneId}", droneId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(medications)))
                .andExpect(status().isNoContent());

        verify(droneService, times(1)).loadMultipleMedications(droneId, medications);
    }

    @Test
    void testGetAvailableDronesForLoading() throws Exception {
        when(droneService.getAvailableDronesForLoading()).thenReturn(availableDrones);

        mockMvc.perform(get("/get-available-drones-for-loading"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].serialNumber").value("SN12TYOI93"))
                .andExpect(jsonPath("$[1].serialNumber").value("SNRTYIU456"));

        verify(droneService, times(1)).getAvailableDronesForLoading();
    }

    @Test
    void testGetBatteryLevel() throws Exception {
        when(droneService.getBatteryLevel(droneId)).thenReturn(drone.getBatteryCapacity());

        mockMvc.perform(get("/get-battery-level/{droneId}", droneId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(drone.getBatteryCapacity()));

        verify(droneService, times(1)).getBatteryLevel(droneId);
    }

    @Test
    void testGetLoadedMedicationFromDrone() throws Exception {
        when(droneService.getLoadedMedications(droneId)).thenReturn(medications);

        mockMvc.perform(get("/get-loaded-medication-from-drone/{droneId}", droneId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(medications.get(0).getName()))
                .andExpect(jsonPath("$[1].name").value(medications.get(1).getName()));

        verify(droneService, times(1)).getLoadedMedications(droneId);
    }
}

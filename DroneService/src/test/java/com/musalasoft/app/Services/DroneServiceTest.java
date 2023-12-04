package com.musalasoft.app.Services;

import com.musalasoft.app.Controllers.DroneController;
import com.musalasoft.app.Interfaces.DroneServiceInterface;
import com.musalasoft.app.Models.Drone;
import com.musalasoft.app.Models.DroneState;
import com.musalasoft.app.Models.Medication;
import com.musalasoft.app.Repositories.DroneRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;


public class DroneServiceTest {
    @Mock
    private DroneRepository droneRepository;

    @Mock
    private DroneService droneService;

    @InjectMocks
    private DroneController droneController;

    private DroneServiceInterface droneServiceInterface;

    private AutoCloseable autoCloseable;

    Long droneId;

    private Drone drone;
    private List<Drone> idleDrones;

    private DroneState state;

    private Medication medication;
    private Medication medicationForLoading1;
    private Medication medicationForLoading2;

    private List<Medication> medications;

    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        droneId = 1l;
        droneServiceInterface = new DroneService(droneRepository);
        droneController       = new DroneController(droneService);

        drone       = new Drone("SN687903YIUO3", "Middleweight", 400.0, 100, state.IDLE);
        idleDrones  = Collections.singletonList(
                new Drone("7823do00-07", "Heavyweight", 370.0, 53, state.IDLE)
        );

        medication  = new Medication("Amatem", "389YUUS", 12.0, "https://imageUrl");
        medications = Arrays.asList(
                new Medication("Amatem", "389YUUS", 12.0, "https://amartemImageUrl"),
                new Medication("Coartem", "COR47889BF", 8.0, "https://coartemImageUrl"),
                new Medication("Ciprotab", "CPT47889CF", 3.0, "https://ciprotabImageUrl")
        );

        medicationForLoading1 = new Medication("Combantrin", "CMB45763700", 10.0, "https://combantinImgaeUrl");
        medicationForLoading2 = new Medication("Mesotine", "MST6738079456", 15.0, "https://mesotineImgaeUrl");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }




    @Test
    void testGetAllDrones(){
        List<Drone> drones = Collections.singletonList(drone);
        when(droneRepository.findAll()).thenReturn(drones);

        List<Drone> allDrones = droneServiceInterface.allDrones();
        assertThat(drones.size()).isEqualTo(allDrones.size());

        assertThat(allDrones.get(0).getSerialNumber()).isEqualTo( drone.getSerialNumber());
    }


    @Test
    void testGetDrone(){
        mock(Drone.class);
        mock(DroneRepository.class);

        when(droneRepository.findById(droneId)).thenReturn(Optional.ofNullable(drone));
        assertThat(droneServiceInterface.getDrone(droneId).getSerialNumber()).isEqualTo(drone.getSerialNumber());
    }

    @Test
    void testCreateRegisterDrone(){
        mock(Drone.class);
        mock(DroneRepository.class);

        when(droneRepository.save(drone)).thenReturn(drone);
        assertThat(droneServiceInterface.registerDrone(drone)).isEqualTo(drone);
    }


    @Test
    void testLoadMedication() {
        when(droneRepository.findById(droneId)).thenReturn(Optional.of(drone));
        when(droneRepository.save(any(Drone.class))).thenReturn(drone);


        droneServiceInterface.loadMedication(droneId, medication);

        verify(droneRepository, times(1)).findById(droneId);
        verify(droneRepository, times(1)).save(drone);


        assertThat(droneServiceInterface.getDrone(droneId).getSerialNumber()).isEqualTo(drone.getSerialNumber());


        org.assertj.core.api.Assertions.assertThat(drone.getLoadedMedications()).contains(medication);
        assertThat(drone.getLoadedMedications().get(0).getId()).isEqualTo(medication.getId());
        assertThat(drone.getLoadedMedications().get(0).getName()).isEqualTo(medication.getName());
        assertThat(drone.getLoadedMedications().get(0).getCode()).isEqualTo(medication.getCode());
        assertThat(drone.getLoadedMedications().get(0).getWeight()).isEqualTo(medication.getWeight());
        assertThat(drone.getLoadedMedications().get(0).getImage()).isEqualTo(medication.getImage());
        assertThat(drone.getState()).isEqualTo(state.LOADED);

    }

    @Test
    void testLoadMultipleMedications() {
        when(droneRepository.findById(droneId)).thenReturn(Optional.of(drone));
        when(droneRepository.save(any(Drone.class))).thenReturn(drone);

        droneServiceInterface.loadMultipleMedications(droneId, medications);

        verify(droneRepository, times(1)).findById(droneId);
        verify(droneRepository, times(1)).save(drone);

        org.assertj.core.api.Assertions.assertThat(drone.getLoadedMedications()).containsExactlyElementsOf(medications);
        assertThat(drone.getState()).isEqualTo(state.LOADED);
    }

    @Test
    void testAvailableDronesForLoading(){

        when(droneRepository.findByState(state.IDLE)).thenReturn(idleDrones);

        List<Drone> availableIdleDrones = droneServiceInterface.getAvailableDronesForLoading();

        assertThat(availableIdleDrones.size()).isEqualTo(1);
        assertThat(availableIdleDrones.get(0).getSerialNumber()).isEqualTo(idleDrones.get(0).getSerialNumber());
        assertThat(availableIdleDrones.get(0).getBatteryCapacity()).isEqualTo(idleDrones.get(0).getBatteryCapacity());
        assertThat(availableIdleDrones.get(0).getState()).isEqualTo(idleDrones.get(0).getState());
        assertThat(availableIdleDrones.get(0).getModel()).isEqualTo(idleDrones.get(0).getModel());
    }


    @Test
    void testGetBatteryLevelOfDrone(){
        when(droneRepository.findDroneBatteryCapacityById(droneId)).thenReturn(drone.getBatteryCapacity());

        int droneBatteryLevel = droneServiceInterface.getBatteryLevel(droneId);

        assertThat( droneBatteryLevel).isEqualTo(drone.getBatteryCapacity());
    }

    @Test
    void testGetLoadedMedicationsFromADrone(){
        when(droneRepository.findById(droneId)).thenReturn(Optional.of(drone));
        drone.getLoadedMedications().addAll(Arrays.asList(medicationForLoading1, medicationForLoading2));


        Medication expectedMedicationForLoading1 = new Medication("Combantrin", "CMB45763700", 10.0, "https://combantinImgaeUrl");
        Medication expectedMedicationForLoading2 = new Medication("Mesotine", "MST6738079456", 15.0, "https://mesotineImgaeUrl");


        List<Medication> resultMedications = droneServiceInterface.getLoadedMedications(droneId);

        org.assertj.core.api.Assertions.assertThat(resultMedications).containsExactlyInAnyOrder(expectedMedicationForLoading1, expectedMedicationForLoading2);

    }
}

package com.musalasoft.app.Services;

import com.musalasoft.app.Controllers.DroneController;
import com.musalasoft.app.Controllers.MedicationController;
import com.musalasoft.app.Interfaces.DroneServiceInterface;
import com.musalasoft.app.Interfaces.MedicationServiceInterface;
import com.musalasoft.app.Models.Drone;
import com.musalasoft.app.Models.Medication;
import com.musalasoft.app.Repositories.DroneRepository;
import com.musalasoft.app.Repositories.MedicationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MedicationServiceTest {

    @Mock
    private MedicationRepository medicationRepository;

    @Mock
    private MedicationService medicationService;

    @InjectMocks
    private MedicationController medicationController;

    private MedicationServiceInterface medicationServiceInterface;

    private AutoCloseable autoCloseable;

    Long medicationId;

    private Medication medication;

    private List<Medication> medications;

    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        medicationId = 1l;
        medicationServiceInterface = new MedicationService(medicationRepository);
        medicationController       = new MedicationController(medicationService);

        medication  = new Medication("Amatem", "389YUUS", 12.0, "https://imageUrl");
  }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }


    @Test
    void testAddMedication(){
        mock(Medication.class);
        mock(MedicationRepository.class);

        when(medicationRepository.save(medication)).thenReturn(medication);
        assertThat(medicationServiceInterface.addMedication(medication)).isEqualTo(medication);
    }



}

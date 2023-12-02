package com.musalasoft.app.Services;

import com.musalasoft.app.Interfaces.MedicationServiceInterface;
import com.musalasoft.app.Models.Drone;
import com.musalasoft.app.Models.DroneState;
import com.musalasoft.app.Models.Medication;
import com.musalasoft.app.Repositories.MedicationRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;



@Service
public class MedicationService implements MedicationServiceInterface {

    private MedicationRepository medicationRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicationService.class);

    public MedicationService(MedicationRepository medicationRepository){
        this.medicationRepository = medicationRepository;
        initializeMedication();
    }

    @PostConstruct
    public void initializeMedication() {
        if (medicationRepository.count() == 0 ) {
            Medication aspirin = new Medication("Aspirin", "SRN6894",32.0,"https://medImage1");
            Medication tylenol = new Medication("Tylenol", "SRN6895",12.0, "https://medImage2");
            Medication spikevax = new Medication("Spikevax", "SRN6896",25.0, "https://medImage3");
            Medication oxfordAstraZeneca = new Medication("Oxford/AstraZeneca", "SRN6897",40.0, "https://medImage4");
            Medication gamaleya = new Medication("Gamaleya", "SRN6898",45.0, "https://medImage5");

            medicationRepository.saveAll(Arrays.asList(aspirin, tylenol,spikevax,oxfordAstraZeneca, gamaleya));

            LOGGER.info("Initialized default drones.");
        }
    }

    @Override
    public Medication addMedication(Medication medication) {
        return medicationRepository.save(medication);
    }
}

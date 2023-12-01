package com.musalasoft.app.Services;

import com.musalasoft.app.Exceptions.BatteryExceptions.BatteryLevelException;
import com.musalasoft.app.Exceptions.DroneExceptions.DroneNotFoundException;
import com.musalasoft.app.Exceptions.DroneExceptions.DroneStateException;
import com.musalasoft.app.Exceptions.WeightExceptions.WeightLimitExceededException;
import com.musalasoft.app.Interfaces.DroneServiceInterface;
import com.musalasoft.app.Models.Drone;
import com.musalasoft.app.Models.DroneState;
import com.musalasoft.app.Models.Medication;
import com.musalasoft.app.Repositories.DroneRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static com.musalasoft.app.Utils.RequirementValidation.isDroneStateValid;


@Service
@AllArgsConstructor
@Transactional
public class DroneService implements DroneServiceInterface {

    private DroneRepository droneRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(DroneService.class);


    public Drone getDrone(Long droneId){
        Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new DroneNotFoundException("Drone with id: " + droneId + " not found."));
        return drone;
    }

    public Drone registerDrone(Drone drone){
        if(!isDroneStateValid(drone.getState().name())){
            throw new DroneStateException("Invalid state. Valid states are: " + Arrays.toString(DroneState.values()));
        }
        return droneRepository.save(drone);
    }

    public List<Drone> getAvailableDronesForLoading() {
        return droneRepository.findByState(DroneState.IDLE);
    }

    @Override
    public int checkBatteryLevel(Long droneId) {
        Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new DroneNotFoundException("Drone with id: " + droneId + " not found."));
        return drone.getBatteryCapacity();
    }

    @Override
    @Scheduled(fixedRate = 3600000)
    public void checkAllDronesBatteryLevelsAndLog(){
        List<Drone> drones = droneRepository.findAll();

        for (Drone drone : drones) {
            if (drone.getBatteryCapacity() < 25) {
                droneBatteryLevelLog(drone, "Battery level below 25%");
            }
        }
    }

    @Override
    public void loadMedication(Long droneId, Medication medication) {
        Drone drone = getDrone(droneId);
        validateDroneLoadStateAndBatteryCapacityMedication(drone, medication);
        drone.getLoadedMedications().add(medication);
        drone.setState(DroneState.LOADED);
        droneRepository.save(drone);
    }

    @Override
    public List<Medication> getLoadedMedication(Long droneId) {
        Drone drone = getDrone(droneId);
        return drone.getLoadedMedications();
    }

    @Override
    public void validateDroneLoadStateAndBatteryCapacityMedication(Drone drone, Medication medication) {
        if (drone.getState() != DroneState.IDLE) {
            throw new DroneStateException("Drone must be in IDLE state to load medication");
        }

        if (drone.getBatteryCapacity() < 25) {
            throw new BatteryLevelException("Drone cannot be loaded with battery level below 25%");
        }

        double totalWeight = calculateTotalWeightLoaded(drone);

        if (totalWeight + medication.getWeight() > drone.getWeightLimit()) {
            throw new WeightLimitExceededException("Loading medication exceeds weight limit");
        }
    }

    @Override
    public double calculateTotalWeightLoaded(Drone drone) {
        List<Medication> loadedMedications = drone.getLoadedMedications();
        double totalWeight = 0.0;

        for (Medication medication : loadedMedications) {
            totalWeight += medication.getWeight();
        }

        return totalWeight;
    }

    public void droneBatteryLevelLog(Drone drone, String message){
        LOGGER.info("Event for Drone ID {}: {}", drone.getId(), message);
    }

}

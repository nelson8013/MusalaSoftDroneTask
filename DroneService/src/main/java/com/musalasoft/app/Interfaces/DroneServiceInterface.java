package com.musalasoft.app.Interfaces;

import com.musalasoft.app.Models.Drone;
import com.musalasoft.app.Models.DroneState;
import com.musalasoft.app.Models.Medication;

import java.util.List;

public interface DroneServiceInterface {

    Drone getDrone(Long droneId);

    List<Drone> allDrones();
    Drone registerDrone(Drone drone);

    public List<Drone> getAvailableDronesForLoading();

    void loadMedication(Long droneId, Medication medication);


    public void loadMultipleMedications(Long droneId, List<Medication> medications);

    List<Medication> getLoadedMedication(Long droneId);

    void validateDroneLoadStateAndBatteryCapacity(Drone drone, Medication medication);

    double calculateTotalWeightLoaded(Drone drone);

    int checkBatteryLevel(Long droneId);

    void checkAllDronesBatteryLevelsAndLog();

    void droneBatteryLevelLog(Long id, String message);

    Integer getBatteryLevel(Long droneId);

    List<Medication> getLoadedMedications(Long droneId);

}

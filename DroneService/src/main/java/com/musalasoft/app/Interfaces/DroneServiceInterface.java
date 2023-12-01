package com.musalasoft.app.Interfaces;

import com.musalasoft.app.Models.Drone;
import com.musalasoft.app.Models.Medication;

import java.util.List;

public interface DroneServiceInterface {

    Drone getDrone(Long droneId);
    Drone registerDrone(Drone drone);

    public List<Drone> getAvailableDronesForLoading();
    void loadMedication(Long droneId, Medication medication);
    List<Medication> getLoadedMedication(Long droneId);

    void validateDroneLoadStateAndBatteryCapacityMedication(Drone drone, Medication medication);

    double calculateTotalWeightLoaded(Drone drone);

    int checkBatteryLevel(Long droneId);

    void checkAllDronesBatteryLevelsAndLog();

    void droneBatteryLevelLog(Drone drone, String message);


}

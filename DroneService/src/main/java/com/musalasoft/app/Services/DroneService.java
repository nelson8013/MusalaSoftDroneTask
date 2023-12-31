    package com.musalasoft.app.Services;

    import static com.musalasoft.app.Utils.RequirementValidation.*;

    import java.time.LocalDateTime;
    import java.time.format.DateTimeFormatter;
    import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.musalasoft.app.Exceptions.BatteryExceptions.BatteryLevelException;
import com.musalasoft.app.Exceptions.DroneExceptions.DroneNotFoundException;
import com.musalasoft.app.Exceptions.DroneExceptions.DroneStateException;
import com.musalasoft.app.Exceptions.SerialNumberException.SerialNumberLimitExceededException;
import com.musalasoft.app.Exceptions.WeightExceptions.WeightLimitExceededException;
import com.musalasoft.app.Interfaces.DroneServiceInterface;
import com.musalasoft.app.Models.Drone;
import com.musalasoft.app.Models.DroneState;
import com.musalasoft.app.Models.Medication;
import com.musalasoft.app.Repositories.DroneRepository;

import jakarta.annotation.PostConstruct;


    @Service
    @Transactional
    public class DroneService implements DroneServiceInterface {

        private final DroneRepository droneRepository;

        private static final Logger LOGGER = LoggerFactory.getLogger(DroneService.class);

        @Autowired
        public DroneService(DroneRepository droneRepository){
            this.droneRepository = droneRepository;
            initializeDrones();
        }

        @PostConstruct
        public void initializeDrones() {
            if (droneRepository.count() == 0) {
                Drone defaultDrone1 = new Drone("7823Uhjdo00-00","Cruiserweight",400.00, 100, DroneState.IDLE);
                Drone defaultDrone2 = new Drone("7823Uhjdo00-01","Middleweight",300.00, 100, DroneState.IDLE);

                droneRepository.saveAll(Arrays.asList(defaultDrone1, defaultDrone2));

                LOGGER.info("Initialized default drones.");
            }
        }


        public Drone getDrone(Long droneId) {
            Optional<Drone> optionalDrone = droneRepository.findById(droneId);
        
            if (optionalDrone.isPresent()) {
                return optionalDrone.get();
            } else {
                throw new DroneNotFoundException("Drone with id: " + droneId + " not found.");
            }
        }

        @Override
        public List<Drone> allDrones() {
            return droneRepository.findAll();
        }


        public Drone registerDrone(Drone drone){
            if(!isDroneStateValid(drone.getState().name())){
                throw new DroneStateException("Invalid state. Valid states are: " + Arrays.toString(DroneState.values()));
            }

            if(!isDroneSerialNumberCharsLessThanOneHundred(drone.getSerialNumber())){
                throw new SerialNumberLimitExceededException("Serial number must be less than 100 chars long.");
            }
            return droneRepository.save(drone);
        }

        public List<Drone> getAvailableDronesForLoading() {
            return droneRepository.findByState(DroneState.IDLE);
        }

        @Override
        public int checkBatteryLevel(Long droneId) {
            Drone drone = getDrone(droneId);
            return drone.getBatteryCapacity();
        }

        @Override
        @Scheduled(fixedRate = 60000)
        public void checkAllDronesBatteryLevelsAndLog(){
            List<Drone> drones = droneRepository.findAll();
            LOGGER.info("DRONE BATTERY CHECK AT:::: " + dateFormat());
            for (Drone drone : drones) {
                Long droneId = drone.getId();
                LOGGER.info("Battery Event for Drone with serial number - {}, capacity - {} %", drone.getSerialNumber(), drone.getBatteryCapacity());
            }
        }

        @Override
        public void loadMedication(Long droneId, Medication medication) {
            Drone drone = getDrone(droneId);
            validateDroneLoadStateAndBatteryCapacity(drone, medication);
            drone.getLoadedMedications().add(medication);
            drone.setState(DroneState.LOADING);
            drone.setState(DroneState.LOADED);
            droneRepository.save(drone);
        }

        public void loadMultipleMedications(Long droneId, List<Medication> medications) {
            Drone drone = getDrone(droneId);

            for (Medication medication : medications) {
                validateDroneLoadStateAndBatteryCapacity(drone, medication);
                drone.getLoadedMedications().add(medication);
            }

            drone.setState(DroneState.LOADING);
            drone.setState(DroneState.LOADED);
            droneRepository.save(drone);
        }


        @Override
        public List<Medication> getLoadedMedication(Long droneId) {
            Drone drone = getDrone(droneId);
            return drone.getLoadedMedications();
        }

        @Override
        public void validateDroneLoadStateAndBatteryCapacity(Drone drone, Medication medication) {
            if (drone.getState() != DroneState.IDLE) {
                throw new DroneStateException("This Drone is in use. Drone must be IDLE to load medication");
            }

            if (drone.getBatteryCapacity() < 25) {
                throw new BatteryLevelException("Drone cannot be loaded. Battery level is below 25%");
            }


            if (medication.getWeight() > drone.getWeightLimit()) {
                throw new WeightLimitExceededException("Loaded medication exceeds weight limit of " + drone.getWeightLimit() + " Grams");
            }
        }


        @Override
        public Integer getBatteryLevel(Long droneId) {
            return droneRepository.findDroneBatteryCapacityById(droneId);
        }

        public List<Medication> getLoadedMedications(Long droneId) {
            Drone drone = getDrone(droneId);
            return new ArrayList<>(drone.getLoadedMedications());
        }

        private  String dateFormat(){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm");
            return formatter.format(LocalDateTime.now());
        }


    }

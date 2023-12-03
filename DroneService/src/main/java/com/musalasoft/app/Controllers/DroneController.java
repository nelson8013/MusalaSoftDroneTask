package com.musalasoft.app.Controllers;

import com.musalasoft.app.Dtos.Requests.DroneRequest;
import com.musalasoft.app.Models.Drone;
import com.musalasoft.app.Models.Medication;
import com.musalasoft.app.Services.DroneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class DroneController {


    private final DroneService droneService;


    @GetMapping("drone/{droneId}")
    public ResponseEntity<Drone> getDrone(@PathVariable(name = "droneId") Long droneId) {
        Drone drone = droneService.getDrone(droneId);

        return new ResponseEntity<>(drone, HttpStatus.OK);
    }

    @GetMapping("drones")
    public ResponseEntity<List<Drone>> drones() {
        List<Drone> drones = droneService.allDrones();

        return new ResponseEntity<>(drones, HttpStatus.OK);
    }


    @PostMapping("/register-drone")
    public ResponseEntity<Drone> registerDrone(@RequestBody DroneRequest request){
        Drone drone = new Drone(request);

        droneService.registerDrone(drone);
        return new ResponseEntity<>(drone, HttpStatus.CREATED);
    }

    @PostMapping("/load-medication/{droneId}")
    public ResponseEntity<Drone> loadMedication(@PathVariable(name = "droneId") Long droneId, @RequestBody Medication medication){
        droneService.loadMedication(droneId, medication);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/load-medications/{droneId}")
    public ResponseEntity<Drone> loadMultipleMedications(@PathVariable(name = "droneId") Long droneId, @RequestBody List<Medication> medications) {
        droneService.loadMultipleMedications(droneId, medications);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping("/get-available-drones-for-loading")
    public ResponseEntity<List<Drone>> getAvailableDronesForLoading(){
        List<Drone> availableDrones = droneService.getAvailableDronesForLoading();

        return new ResponseEntity<>(availableDrones, HttpStatus.OK);
    }


    @GetMapping("/get-battery-level/{droneId}")
    public ResponseEntity<Integer> getBatteryLevel(@PathVariable(name = "droneId") Long droneId){
        int batteryLevel = droneService.getBatteryLevel(droneId);
        return new ResponseEntity<>(batteryLevel, HttpStatus.OK);
    }

    @GetMapping("/get-loaded-medication-from-drone/{droneId}")
    public ResponseEntity<List<Medication>> getLoadedMedicationFromDrone(@PathVariable(name = "droneId") Long droneId){
        List<Medication>  medications =  droneService.getLoadedMedications(droneId);
        return new ResponseEntity<>(medications, HttpStatus.OK);
    }


}

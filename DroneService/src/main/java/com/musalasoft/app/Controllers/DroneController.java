package com.musalasoft.app.Controllers;

import com.musalasoft.app.Dtos.Requests.DroneRequest;
import com.musalasoft.app.Models.Drone;
import com.musalasoft.app.Models.DroneState;
import com.musalasoft.app.Models.Medication;
import com.musalasoft.app.Services.DroneService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.musalasoft.app.Utils.RequirementValidation.isDroneStateValid;

@RestController
@RequiredArgsConstructor
public class DroneController {


    private final DroneService droneService;

//    public DroneController(DroneService droneService) {
//        this.droneService = droneService;
//    }


    @GetMapping("drone/{droneId}")
    public Drone getDrone(@PathVariable("droneId") Long droneId) {
        return droneService.getDrone(droneId);
    }

    @GetMapping("drones")
    public List<Drone> drones() {
        return droneService.allDrones();
    }


    @PostMapping("/register-drone")
    public ResponseEntity<Drone> registerDrone(@RequestBody DroneRequest request){
        Drone drone = new Drone(request);

        droneService.registerDrone(drone);
        return new ResponseEntity<>(drone, HttpStatus.CREATED);
    }

    @PostMapping("/load-medication/{droneId}")
    public ResponseEntity<Drone> loadMedication(@PathVariable Long droneId, @RequestBody Medication medication){
        droneService.loadMedication(droneId, medication);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

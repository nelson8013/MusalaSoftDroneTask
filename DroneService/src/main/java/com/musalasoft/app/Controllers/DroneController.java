package com.musalasoft.app.Controllers;

import com.musalasoft.app.Dtos.Requests.DroneRequest;
import com.musalasoft.app.Models.Drone;
import com.musalasoft.app.Models.DroneState;
import com.musalasoft.app.Services.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.musalasoft.app.Utils.RequirementValidation.isDroneStateValid;

@RestController
public class DroneController {

    @Autowired
    private DroneService droneService;

    @PostMapping("/register-drone")
    public ResponseEntity<Drone> registerDrone(@RequestBody DroneRequest request){
        Drone drone = new Drone(request);

        droneService.registerDrone(drone);
        return new ResponseEntity<>(drone, HttpStatus.CREATED);
    }

    @PostMapping("/load-medication")
    public ResponseEntity<> loadMedication(){

    }

}

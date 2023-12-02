package com.musalasoft.app.Controllers;


import com.musalasoft.app.Dtos.Requests.MedicationRequest;

import com.musalasoft.app.Models.Medication;
import com.musalasoft.app.Repositories.MedicationRepository;
import com.musalasoft.app.Services.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
public class MedicationController {

    private final MedicationService medicationService;

    @PostMapping("/add-medication")
    public ResponseEntity<Medication> addMedication(@RequestBody MedicationRequest request){

        Medication medication = new Medication(request);

        medicationService.addMedication(medication);

        return new ResponseEntity<>(medication, HttpStatus.CREATED);
    }
}

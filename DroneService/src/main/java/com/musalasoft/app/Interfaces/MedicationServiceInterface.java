package com.musalasoft.app.Interfaces;

import com.musalasoft.app.Models.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationServiceInterface  {

    Medication addMedication(Medication medication);
}

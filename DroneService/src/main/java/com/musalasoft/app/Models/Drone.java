package com.musalasoft.app.Models;

import static com.musalasoft.app.Utils.RequirementValidation.*;

import java.util.ArrayList;
import java.util.List;

import com.musalasoft.app.Dtos.Requests.DroneRequest;
import com.musalasoft.app.Exceptions.SerialNumberException.SerialNumberLimitExceededException;
import com.musalasoft.app.Exceptions.WeightExceptions.WeightLimitExceededException;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "drone")
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String serialNumber;
    private String model;
    private double weightLimit;
    private int batteryCapacity;

    @Enumerated(EnumType.STRING)
    private DroneState state;

    @OneToMany( cascade = CascadeType.ALL)
    private List<Medication> loadedMedications = new ArrayList<>();




    public Drone(){}

    public Drone(String serialNumber, String model, double weightLimit, int batteryCapacity, DroneState state) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
    }

    public Drone(DroneRequest request){
        this.serialNumber    = request.getSerialNumber();
        this.model           = request.getModel();
        this.weightLimit     = request.getWeightLimit();
        this.batteryCapacity = request.getBatteryCapacity();
        this.state           = request.getState();
    }







    public void setId(Long id) {
     this.id = id;
    }

    public Long getId() {
     return this.id;
    }


    public void setSerialNumber(String serialNumber) {
     if (!isSerialNumberValid(serialNumber)) {
      throw new SerialNumberLimitExceededException("Serial number exceeds maximum length of 100 characters");
     }
     this.serialNumber = serialNumber;
    }
    public String getSerialNumber() {
     return this.serialNumber;
    }


    public void setModel(String model) {

        if (!isModelValid(model)) {
         throw new IllegalArgumentException("Invalid drone model");
        }
        this.model = model;
   }
    public String getModel() {
        return this.model;
    }



    public void setWeightLimit(double weightLimit) {
        if (!isWeightLimitValid(weightLimit)) {
         throw new WeightLimitExceededException("Weight limit must be between 0 and 500 grams.");
        }
        this.weightLimit = weightLimit;
    }
    public double getWeightLimit() {
        return this.weightLimit;
    }


    public void setBatteryCapacity(int batteryCapacity) {

        if (batteryCapacity >= 0 && batteryCapacity <= 100) {
         this.batteryCapacity = batteryCapacity;
        } else {
         throw new IllegalArgumentException("Battery capacity must be between 0 and 100");
        }

   }
    public int getBatteryCapacity() {
        return this.batteryCapacity;
    }



    public void setState(DroneState state) {
        this.state = state;
    }
    public DroneState getState() {
        return this.state;
    }


    public List<Medication> getLoadedMedications() {
        return this.loadedMedications;
    }

}

package com.musalasoft.app.Models;

import com.musalasoft.app.Exceptions.SerialNumberException.SerialNumberLimitExceededException;
import com.musalasoft.app.Exceptions.WeightExceptions.WeightLimitExceededException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import static com.musalasoft.app.Utils.RequirementValidation.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "drone")
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String serialNumber;
    private String model;
    private double weightLimit;
    private int batteryCapacity;
    private DroneState state;

 public Long getId() {
  return this.id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public String getSerialNumber() {
  return this.serialNumber;
 }

 public void setSerialNumber(String serialNumber) {
  if (!isSerialNumberValid(serialNumber)) {
   throw new SerialNumberLimitExceededException("Serial number exceeds maximum length of 100 characters");
  }
  this.serialNumber = serialNumber;
 }

 public String getModel() {
  return this.model;
 }

 public void setModel(String model) {

  if (!isModelValid(model)) {
      throw new IllegalArgumentException("Invalid drone model");
  }
   this.model = model;
 }

 public double getWeightLimit() {
  return this.weightLimit;
 }

 public void setWeightLimit(double weightLimit) {
  if (!isWeightLimitValid(weightLimit)) {
      throw new WeightLimitExceededException("Weight limit must be between 0 and 500 grams.");
  }
  this.weightLimit = weightLimit;
 }

 public int getBatteryCapacity() {
  return this.batteryCapacity;
 }

 public void setBatteryCapacity(int batteryCapacity) {

  if (batteryCapacity >= 0 && batteryCapacity <= 100) {
   this.batteryCapacity = batteryCapacity;
  } else {
   throw new IllegalArgumentException("Battery capacity must be between 0 and 100");
  }

 }

 public DroneState getState() {
  return this.state;
 }

 public void setState(DroneState state) {
  this.state = state;
 }


}

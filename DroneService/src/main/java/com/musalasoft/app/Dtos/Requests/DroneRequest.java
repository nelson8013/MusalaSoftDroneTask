package com.musalasoft.app.Dtos.Requests;

import com.musalasoft.app.Models.DroneState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DroneRequest {

    private String serialNumber;
    private String model;
    private double weightLimit;
    private int batteryCapacity;
    private DroneState state;
}

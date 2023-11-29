package com.musalasoft.app.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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
}

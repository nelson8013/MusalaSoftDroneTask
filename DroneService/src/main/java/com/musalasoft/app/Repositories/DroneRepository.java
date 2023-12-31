package com.musalasoft.app.Repositories;

import com.musalasoft.app.Models.Drone;
import com.musalasoft.app.Models.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {
    List<Drone> findByState(DroneState state);

    @Query("SELECT d.batteryCapacity FROM Drone d WHERE d.id = :droneId")
    Integer findDroneBatteryCapacityById(@Param("droneId") Long droneId);
}

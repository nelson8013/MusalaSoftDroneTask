package com.musalasoft.app.Repositories;


import com.musalasoft.app.Models.Drone;
import com.musalasoft.app.Models.DroneState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DroneRepositoryTest {

    @Autowired
    private DroneRepository droneRepository;
    Drone idleDrone;
    Drone loadedDrone;

    private DroneState state;


    @BeforeEach
    void setUp(){
        idleDrone  = new Drone("SN687903YIUO3", "Middleweight", 400.0, 100, state.IDLE);
        loadedDrone  = new Drone("7823Uhjdo00-00", "Cruiserweight", 400.0, 100, state.LOADED);

        droneRepository.save(idleDrone);
        droneRepository.save(loadedDrone);
    }

    @AfterEach
    void tearDown(){
        idleDrone = null;
        loadedDrone = null;
        droneRepository.deleteAll();
    }

    @Test
    void testFindByIdleState_Found(){
        List<Drone> drones =  droneRepository.findByState(state.IDLE);

        assertThat( drones.get(0).getState()).isEqualTo(idleDrone.getState());
        assertThat( drones.get(0).getModel()).isEqualTo( idleDrone.getModel());
        assertThat( drones.get(0).getSerialNumber()).isEqualTo( idleDrone.getSerialNumber());
        assertThat( drones.get(0).getBatteryCapacity()).isEqualTo( idleDrone.getBatteryCapacity());
    }

    @Test
    void testFindByLoadedState_Found(){
        List<Drone> drones =  droneRepository.findByState(state.LOADED);

        assertThat( drones.get(0).getState()).isEqualTo(loadedDrone.getState());
        assertThat( drones.get(0).getModel()).isEqualTo( loadedDrone.getModel());
        assertThat( drones.get(0).getSerialNumber()).isEqualTo( loadedDrone.getSerialNumber());
        assertThat( drones.get(0).getBatteryCapacity()).isEqualTo( loadedDrone.getBatteryCapacity());
    }

    @Test
    void testFindByDeliveredState_NOT_Found(){
        List<Drone> drones =  droneRepository.findByState(state.DELIVERED);

        assertThat(drones.isEmpty()).isTrue();
    }

    @Test
    void testFindByReturningState_NOT_Found(){
        List<Drone> drones =  droneRepository.findByState(state.RETURNING);

        assertThat(drones.isEmpty()).isTrue();
    }




}

package com.musalasoft.app.Exceptions.DroneExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DroneExceptionHandler {

    @ExceptionHandler( value = {DroneNotFoundException.class})
    public ResponseEntity<Object> handleDroneNotFoundException(DroneNotFoundException droneNotFoundException){
        DroneException exception = new DroneException(droneNotFoundException.getMessage(), droneNotFoundException.getCause(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler( value = {DroneStateException.class})
    public ResponseEntity<Object> handleDroneStateException( DroneStateException droneStateException){
        DroneException exception = new DroneException(droneStateException.getMessage(), droneStateException.getCause(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }
}

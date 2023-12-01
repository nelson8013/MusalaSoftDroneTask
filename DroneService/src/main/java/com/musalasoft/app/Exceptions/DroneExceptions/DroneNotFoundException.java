package com.musalasoft.app.Exceptions.DroneExceptions;

public class DroneNotFoundException extends RuntimeException {

    public DroneNotFoundException(String message) {
        super(message);
    }

    public DroneNotFoundException(Throwable cause){
        super(cause);
    }

    public DroneNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

}

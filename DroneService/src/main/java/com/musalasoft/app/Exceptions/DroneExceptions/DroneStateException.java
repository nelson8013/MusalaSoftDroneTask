package com.musalasoft.app.Exceptions.DroneExceptions;

public class DroneStateException extends RuntimeException {
    public DroneStateException(String message){
        super(message);
    }
    public DroneStateException(Throwable cause){
        super(cause);
    }
    public DroneStateException(String message, Throwable cause){
        super(message, cause);
    }
}

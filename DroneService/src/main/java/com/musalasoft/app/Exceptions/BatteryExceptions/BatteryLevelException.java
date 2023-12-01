package com.musalasoft.app.Exceptions.BatteryExceptions;

public class BatteryLevelException extends RuntimeException{
    public BatteryLevelException(String message){
        super(message);
    }

    public BatteryLevelException(Throwable cause){
        super(cause);
    }
    public BatteryLevelException(String message, Throwable cause){
        super(message, cause);
    }
}

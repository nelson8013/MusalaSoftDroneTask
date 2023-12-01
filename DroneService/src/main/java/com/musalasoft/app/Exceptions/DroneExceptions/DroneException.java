package com.musalasoft.app.Exceptions.DroneExceptions;

import org.springframework.http.HttpStatus;

public class DroneException {

    private final HttpStatus status;
    private final String message;
    private final Throwable cause;


    public DroneException(String message, Throwable cause,HttpStatus status ){
        this.message = message;
        this.cause   = cause;
        this.status  = status;
    }


    public String getMessage() {
        return this.message;
    }


    public Throwable getCause() {
        return this.cause;
    }


    public HttpStatus getStatus() {
        return this.status;
    }
}

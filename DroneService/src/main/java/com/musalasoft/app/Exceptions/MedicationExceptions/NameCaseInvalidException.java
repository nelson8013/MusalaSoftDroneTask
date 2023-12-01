package com.musalasoft.app.Exceptions.MedicationExceptions;

public class NameCaseInvalidException extends RuntimeException {

    public NameCaseInvalidException(String message){
        super(message);
    }

    public NameCaseInvalidException(Throwable cause) {
        super(cause);
    }

    public NameCaseInvalidException(String message, Throwable cause){
        super(message, cause);
    }
}

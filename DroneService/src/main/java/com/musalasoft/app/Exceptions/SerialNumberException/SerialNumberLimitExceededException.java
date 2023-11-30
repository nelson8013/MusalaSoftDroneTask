package com.musalasoft.app.Exceptions.SerialNumberException;

public class SerialNumberLimitExceededException extends RuntimeException {

    public SerialNumberLimitExceededException(String message){
        super(message);
    }

    public SerialNumberLimitExceededException(Throwable cause) {
        super(cause);
    }

    public SerialNumberLimitExceededException(String message, Throwable cause) {
        super(message, cause);
    }

}

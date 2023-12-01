package com.musalasoft.app.Exceptions.MedicationExceptions;

public class CodeCaseInvalidException extends RuntimeException {
    public CodeCaseInvalidException(String message) {
        super(message);
    }

    public CodeCaseInvalidException(Throwable cause) {
        super(cause);
    }

    public CodeCaseInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}

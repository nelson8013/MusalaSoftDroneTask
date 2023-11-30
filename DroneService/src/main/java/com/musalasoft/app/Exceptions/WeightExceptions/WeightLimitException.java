package com.musalasoft.app.Exceptions.WeightExceptions;

import org.springframework.http.HttpStatus;

public class WeightLimitException {

    private final String message;
    private final Throwable cause;
    private final HttpStatus status;


   WeightLimitException(String message, Throwable cause, HttpStatus status ){
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

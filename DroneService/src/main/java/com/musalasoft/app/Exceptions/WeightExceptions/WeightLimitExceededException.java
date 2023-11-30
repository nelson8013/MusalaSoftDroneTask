package com.musalasoft.app.Exceptions.WeightExceptions;

public class WeightLimitExceededException extends RuntimeException {

 public WeightLimitExceededException(String message){
   super(message);
 }

 public WeightLimitExceededException(String message, Throwable cause){
   super(message, cause);
 }
 
}

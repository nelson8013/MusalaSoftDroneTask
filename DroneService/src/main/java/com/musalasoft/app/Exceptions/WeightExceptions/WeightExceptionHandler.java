package com.musalasoft.app.Exceptions.WeightExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WeightExceptionHandler {

  @ExceptionHandler( value = {WeightLimitExceededException.class})
  public ResponseEntity<Object> handleWeightLimitExceededException(WeightLimitExceededException weightLimitExceededException){
    WeightLimitException weightLimitException = new WeightLimitException(weightLimitExceededException.getMessage(), weightLimitExceededException.getCause(), HttpStatus.EXPECTATION_FAILED);
    return new ResponseEntity<Object>(weightLimitException, HttpStatus.EXPECTATION_FAILED);
  }
}

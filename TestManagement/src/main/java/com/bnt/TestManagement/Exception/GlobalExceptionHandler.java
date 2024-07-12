package com.bnt.TestManagement.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler{

  @ExceptionHandler(IdNotFoundException.class)
  public ResponseEntity<Object> handleIdNotFound(IdNotFoundException e){
    return new ResponseEntity<>("Id Not Found ",HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InvalidDataException.class)
  public ResponseEntity<Object> handleInvalidData(InvalidDataException e){
    return new ResponseEntity<Object>("Data Is Invalid ", HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(QuestionModelEmpty.class)
  public ResponseEntity<Object> handleEmptyQuestionModel(QuestionModelEmpty e){
    return new ResponseEntity<Object>("Question model is empty", HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DataIsNotPresent.class)
  public ResponseEntity<Object> DataIsNotPresent(DataIsNotPresent e){
    return new ResponseEntity<Object>("Data is Not present", HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DataAllreadyPresentException.class)
  public ResponseEntity<Object> DataAllreadyPresentException(DataAllreadyPresentException e){
    return new ResponseEntity<Object>("Data Allready present", HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<Object> handleRuntimeException(RuntimeException e) {
      return new ResponseEntity<>("Service error", HttpStatus.INTERNAL_SERVER_ERROR);
  }
    
}

package org.gfg.minor1.ExceptionHandlers;

import org.gfg.minor1.response.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        GenericResponse genericResponse = GenericResponse.builder().statusCode(1).code(HttpStatus.BAD_REQUEST.value()).message(ex.getMessage()).data(null).build();
        ResponseEntity responseEntity = new ResponseEntity(genericResponse, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity handleUnexpectedTypeException(UnexpectedTypeException ex){
        GenericResponse genericResponse = GenericResponse.builder().statusCode(1).code(HttpStatus.BAD_REQUEST.value()).message(ex.getMessage()).data(null).build();
        ResponseEntity responseEntity = new ResponseEntity(genericResponse, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException ex){
        GenericResponse genericResponse = GenericResponse.builder().statusCode(1).code(HttpStatus.BAD_REQUEST.value()).message(ex.getMessage()).data(null).build();
        ResponseEntity responseEntity = new ResponseEntity(genericResponse, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
}

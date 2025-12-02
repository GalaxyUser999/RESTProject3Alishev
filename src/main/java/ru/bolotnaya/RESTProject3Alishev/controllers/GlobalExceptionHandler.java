package ru.bolotnaya.RESTProject3Alishev.controllers;

import jakarta.persistence.NonUniqueResultException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bolotnaya.RESTProject3Alishev.utils.*;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    private ResponseEntity<String> handleResponse(SensorNotFoundException sensorNotFoundException) {
        String response = sensorNotFoundException.getMessage();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<String> handleResponse(SensorNotCreatedException sensorNotCreatedException) {
        String response = sensorNotCreatedException.getMessage();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException ex) {
        StringBuilder errorMessage = new StringBuilder("You've failed to get through the validation: ");
        ex.getConstraintViolations().forEach(violation ->
                errorMessage.append(violation.getPropertyPath()).append(" -> ").append(violation.getMessage()).append("; ")
        );
        return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
    }


    /// /////////////////////Не работает//////////////
//    @ExceptionHandler(NonUniqueResultException.class)
//    public ResponseEntity<String> handleNonUniqueResultException(NonUniqueResultException ex) {
//        String response = ex.getMessage();
//        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
//    }
}


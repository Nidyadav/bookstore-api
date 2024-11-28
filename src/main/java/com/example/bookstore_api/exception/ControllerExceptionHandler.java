package com.example.bookstore_api.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(NoRelevantRecordFoundException.class)
    public ResponseEntity<ErrorMessage> NoRelevantRecordFound(NoRelevantRecordFoundException ex, WebRequest request){
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<ErrorMessage>(message,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String,String>> handleConstraintViolations(ConstraintViolationException constraintViolationException) {
        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<?> violation : constraintViolationException.getConstraintViolations()) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage().toString();
            errors.put(propertyPath, message);
        }
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

}

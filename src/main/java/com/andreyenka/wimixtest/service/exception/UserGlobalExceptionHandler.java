package com.andreyenka.wimixtest.service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
public class UserGlobalExceptionHandler {

    @ExceptionHandler(NoSuchEntityException.class)
    public ResponseEntity<ExceptionDetails> handleException (NoSuchEntityException exception, WebRequest request){
        ExceptionDetails details =
                new ExceptionDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity <ExceptionDetails> handleException (Exception exception, WebRequest request){
        ExceptionDetails details =
                new ExceptionDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionDetails> onConstraintValidationException(
            ConstraintViolationException exception, WebRequest request
    ) {
        ExceptionDetails details =
                new ExceptionDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }
}

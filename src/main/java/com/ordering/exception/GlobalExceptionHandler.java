package com.ordering.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final  ResponseEntity<?> handleAllException(Exception exception, WebRequest webRequest){
        ExceptionDetails exceptionDetails= new ExceptionDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(true));
        return new ResponseEntity<>(exceptionDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public final  ResponseEntity<?> handleUserNotFoundException(Exception exception, WebRequest webRequest){
        ExceptionDetails exceptionDetails= new ExceptionDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(true));
        return new ResponseEntity<>(exceptionDetails,HttpStatus.NOT_FOUND);
    }

}

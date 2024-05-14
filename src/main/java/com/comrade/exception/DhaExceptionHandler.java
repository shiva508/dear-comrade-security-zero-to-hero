package com.comrade.exception;

import com.comrade.model.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class DhaExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> recordNotFound(BadCredentialsException exception){
        return new ResponseEntity<>(ApiErrorResponse.builder()
                .message("Please prov")
                .timestamp(new Date())
                .error(exception.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build(),HttpStatus.NOT_FOUND) ;
    }
}

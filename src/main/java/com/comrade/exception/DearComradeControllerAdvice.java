package com.comrade.exception;

import com.comrade.model.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DearComradeControllerAdvice {

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<HttpResponse> recordNotFound(RecordNotFoundException recordNotFoundException){
        HttpResponse httpResponse = HttpResponse.builder().httpStatus(HttpStatus.NOT_FOUND).httpStatusCode(404)
                .message(recordNotFoundException.getMessage()).build();
        return new ResponseEntity<>(httpResponse, HttpStatus.NOT_FOUND);
    }
}

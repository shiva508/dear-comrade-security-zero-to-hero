package com.comrade.model.exception;

public class JwtCustomException extends RuntimeException{
    public JwtCustomException(String message) {
        super(message);
    }
}

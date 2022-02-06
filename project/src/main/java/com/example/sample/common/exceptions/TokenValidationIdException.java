package com.example.sample.common.exceptions;

import org.springframework.http.HttpStatus;

public class TokenValidationIdException extends RuntimeException{
    private String message ="Not find the ID in the tokens";
    private HttpStatus status;

    public TokenValidationIdException(){

    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}

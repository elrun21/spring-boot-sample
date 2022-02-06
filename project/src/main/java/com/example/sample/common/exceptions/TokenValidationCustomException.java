package com.example.sample.common.exceptions;

import org.springframework.http.HttpStatus;

public class TokenValidationCustomException extends RuntimeException{
    private String message ;
    private HttpStatus status;

    public TokenValidationCustomException(){

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

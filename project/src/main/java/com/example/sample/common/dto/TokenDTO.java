package com.example.sample.common.dto;

import io.jsonwebtoken.Claims;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class TokenDTO {
    private HttpStatus status ;
    private String msg;
    private Claims data;
    private int errCode ;
}

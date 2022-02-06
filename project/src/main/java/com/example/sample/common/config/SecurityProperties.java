package com.example.sample.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Component;


/**
 * JWT 설정값
 */
@Component
@ConstructorBinding
@ConfigurationProperties(prefix = "security.jwt")
@Data
public class SecurityProperties {
    private String secretKey;
    private Long expireTime ;
    private String authKey;
}

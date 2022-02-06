package com.example.sample.common.config;

import com.example.sample.common.handler.CertificationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private CertificationInterceptor interceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .excludePathPatterns("/api/member/user/**")
                .excludePathPatterns("/api/member/sign-up")
                .addPathPatterns("/api/auth/sign-out")
                .addPathPatterns("/api/member/**");

    }
}

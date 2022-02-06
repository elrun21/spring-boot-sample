package com.example.sample.common.handler;

import com.example.sample.common.dto.TokenDTO;
import com.example.sample.common.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class CertificationInterceptor implements HandlerInterceptor {
    @Autowired
    private  JwtUtils jwt ;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if( token == null || token.equals("")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        boolean tokenCheckResult =  checkAuthorization(token) ;
        if( tokenCheckResult == true ) {
            return true ;
        }
        else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
    }

    /**
     * 토큰 검증
     * @param token
     * @return
     */
    private boolean checkAuthorization( String token ) {
        TokenDTO tokenData = jwt.valid(token);
        if( HttpStatus.OK == tokenData.getStatus() ) {
            return true;
        }else {
            log.error(tokenData.getMsg());
            return false;
        }
    }
}

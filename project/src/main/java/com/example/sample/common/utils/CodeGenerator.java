package com.example.sample.common.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@Component
public class CodeGenerator {
    /**
     * 유니크한 랜덤 코드를 생성
     * @param prefix
     * @return
     */
    public String  makeCode( String prefix ){
        if( prefix == null ) throw new IllegalArgumentException("prefix is not null");
        if(prefix.length() > 2)
           throw new IllegalArgumentException("code의 자리수는 2를 초과할 수 없습니다");

        LocalDateTime dateTime = LocalDateTime.now();
        String currentTime = String.valueOf(Timestamp.valueOf(dateTime).getTime()).substring(1,10) ;
        String lastCode = RandomStringUtils.randomAlphanumeric(2);
        return prefix+currentTime+lastCode;
    }
}

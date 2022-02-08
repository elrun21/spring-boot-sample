package com.example.sample.common.utils;

import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
@Component
public class LogUtils {
    /**
     * Error 로그를 알림/로그수집 처리
     * @param e
     * @return
     */
    public String loggerAgent(Exception e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        /**
         * (미구현 )
         *  To do
         *  1)Slack 이나 기타 메신저로 전송
         *  2) 에러 로그를 flunted or filebit 등을 이용하여 수집소로 전송
         */
        e.printStackTrace(pw);

        return sw.toString();
    }

    /**
     * Exception 로그를 단순 콘솔에 출력
     * @param e
     * @return
     */
    public String getErrorLog(Exception e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.append("------------------------------------- ::: DEBUG ::: --------------------------------------\n");
        /** To do
         *
         */
        e.printStackTrace(pw);

        pw.append("---------------------------------------------------------------------------\n");
        return sw.toString();
    }
}

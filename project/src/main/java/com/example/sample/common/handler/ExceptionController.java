package com.example.sample.common.handler;

import com.example.sample.common.exceptions.TokenValidationCustomException;
import com.example.sample.common.exceptions.TokenValidationIdException;
import com.example.sample.common.utils.LogUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
@AllArgsConstructor
@Slf4j
public class ExceptionController {
    private  LogUtils loggerAgent;
    /**
     * 500 에러 발생시 공통 처리 하는 부분
     * @param ex
     * @return
     */
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(final Exception ex) {
        log.error(ex.getClass().getName(), ex); // 기본적인 콘솔로그 기록
        loggerAgent.loggerAgent(ex); // 알림/ 수집 처리
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /** Custom Exception Hander Area **/

    @ExceptionHandler({ MissingRequestHeaderException.class })
    public ResponseEntity<Object> handleAll(final MissingRequestHeaderException ex) {
        log.error("MissingRequestHeaderException", ex);
        return new ResponseEntity<>("Not Found Header property", HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({ NumberFormatException.class })
    public ResponseEntity<Object> handleAll(final NumberFormatException ex) {
        log.error("NumberFormatException", ex);
        return new ResponseEntity<>("Only numbers are allowed", HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleAll(final MethodArgumentTypeMismatchException ex) {
        log.error("MethodArgumentTypeMismatchException", ex);
        return new ResponseEntity<>("Parameter Type Missing", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<Object> handleAll(final IllegalArgumentException ex) {
        log.error("IllegalArgumentException", ex);
        return new ResponseEntity<>("IllegalArgumentException", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ MissingServletRequestParameterException.class })
    public ResponseEntity<Object> handleAll(final MissingServletRequestParameterException ex) {
        log.error("MissingServletRequestParameterException", ex);
        return new ResponseEntity<>("Missing Parameter", HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<Object> handleAll(final MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException", ex);
        return new ResponseEntity<>("Parameter validation failed", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ EntityNotFoundException.class })
    public ResponseEntity<Object> handleAll(final EntityNotFoundException ex) {
        log.error("EntityNotFoundException", ex);
        return new ResponseEntity<>("Not found Infomation", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ TokenValidationCustomException.class })
    public ResponseEntity<Object> handleAll(final TokenValidationCustomException ex) {
        log.error("TokenValidationCustomException", ex);
        return new ResponseEntity<>("Token Validtion Failed", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ TokenValidationIdException.class })
    public ResponseEntity<Object> handleAll(final TokenValidationIdException ex) {
        log.error("TokenValidationIdException", ex);
        return new ResponseEntity<>("Miss matching id and token", HttpStatus.UNAUTHORIZED);
    }


}

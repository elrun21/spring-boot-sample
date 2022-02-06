package com.example.sample.common.utils;

import com.example.sample.common.CommonEnum;
import com.example.sample.common.dto.CommonResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

@Component
public class ResponseUtils {
    /**
     * 성공시 결과값 포함 한 리턴 객체 생성
     * @param result
     * @return ResponseEntity
     */
    public ResponseEntity makeSuccessResponse(Object result  ){
        CommonResponseDTO resData = CommonResponseDTO.builder().code(0).msg("success").content(result).build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName(CommonEnum.COMMON_ENCODE_CHARTER_SET.getValue())));
        return new ResponseEntity<>( resData, headers , HttpStatus.OK );
    }

    /**
     * 바디는 없고 상태값만 전달 하는 객체 생성
     * @param status
     * @return
     */
    public ResponseEntity makeOtherResponse( HttpStatus status  ){
        //CommonResponseDTO resData = CommonResponseDTO.builder().code(0).msg("success").content(null).build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName(CommonEnum.COMMON_ENCODE_CHARTER_SET.getValue())));
        return new ResponseEntity<>(  headers , status );
    }

    /**
     * 상태값과 메세지 , 리턴 데이터를 커스텀 하여 객체를 생성
     * @param status
     * @param msg
     * @param code
     * @return
     */
    public ResponseEntity makeOtherResponse( HttpStatus status , String msg ,int code  ){
        CommonResponseDTO resData = CommonResponseDTO.builder().code(code).msg(msg).content(null).build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName(CommonEnum.COMMON_ENCODE_CHARTER_SET.getValue())));
        return new ResponseEntity<>( resData, headers , status );
    }


}

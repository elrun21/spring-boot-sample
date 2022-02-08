package com.example.sample.domain.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@ApiModel(value = "회원 가입 결과 정보", description = "회원가입후 결과를 전달하기 위한 객체")
@Builder
@Data
public class ResSignUpDTO {
    @ApiModelProperty(value = "사용자 내부 인덱스 키")
    private Long userIdx;

    public ResSignUpDTO(Long userIdx){
        this.userIdx = userIdx;
    }
}

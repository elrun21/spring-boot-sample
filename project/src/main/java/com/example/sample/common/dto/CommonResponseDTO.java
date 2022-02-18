package com.example.sample.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel(value = "공용 응답 데이터 객체" , description = "서비스 전체에서 사용하영 공용 객체 ")
@Getter
@Setter
@ToString
@Builder
public class CommonResponseDTO {
    @ApiModelProperty(value = "처리 결과 메세지")
    private String msg;
    @ApiModelProperty(value = "에러 코드 ( 0 이면 정상처리 그외에는 에러코드 ) ")
    private int code ;
    @ApiModelProperty(value = "처리 결과 데이터")
    private Object content;
    public CommonResponseDTO toEntity() {
        return CommonResponseDTO.builder()
                .code(code)
                .msg(msg)
                .content(content)
                .build();
    }
}

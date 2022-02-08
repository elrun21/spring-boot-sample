package com.example.sample.domain.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@ApiModel(value = "상품등록 결과 정보", description = "상품등록 후 결과를 전달하기 위한 객체")
@Builder
@Data
public class ResSetProductDTO {


    @ApiModelProperty(value = "사용자 내부 인덱스 키")
    private Long productIdx;

    public ResSetProductDTO(Long productIdx) {
        this.productIdx = productIdx;
    }
}

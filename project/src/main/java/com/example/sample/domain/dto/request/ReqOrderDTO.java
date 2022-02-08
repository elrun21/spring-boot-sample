package com.example.sample.domain.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "회원 상세 정보(단건)")
@Getter
public class ReqOrderDTO {
    @NotNull
    @ApiModelProperty(value = "사용자 식별키 "   , required = true)
    private Long userIdx;
    @NotNull
    @ApiModelProperty(value = "상품정보키"   , required = true)
    private Long productIdx;
    @ApiModelProperty(value = "구매수량"   , required = true)
    private int productCount;

    @ApiModelProperty(value = "배송지"   , required = true)
    private String address;
    @ApiModelProperty(value = "수령인"   , required = true)
    private String receiver;
    @ApiModelProperty(value = "배송 연락처"   , required = true)
    private String phone;
    @ApiModelProperty(value = "결제수단"   , required = true)
    private String paymentType;

}

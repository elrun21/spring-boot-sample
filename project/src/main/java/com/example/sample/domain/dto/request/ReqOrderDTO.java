package com.example.sample.domain.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel(value = "회원 상세 정보(단건)")
@Getter
@Setter
public class ReqOrderDTO {
    @NotNull
    @ApiModelProperty(value = "사용자 식별키 "   , required = true)
    private Long userIdx;

    @NotNull
    @ApiModelProperty(value = "상품정보"   , required = true)
    private List<SaleProductInfo> productInfo;

    @NotBlank
    @ApiModelProperty(value = "배송지"   , required = true)
    private String address;

    @NotBlank
    @ApiModelProperty(value = "수령인"   , required = true)
    private String receiver;

    @NotBlank
    @ApiModelProperty(value = "배송 연락처"   , required = true)
    private String phone;

    @NotBlank
    @ApiModelProperty(value = "결제수단"   , required = true)
    private String paymentType;

   

}

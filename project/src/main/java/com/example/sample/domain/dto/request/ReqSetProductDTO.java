package com.example.sample.domain.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel(value = "상품 등록", description = "상품 등록시 사용되는 정보")
@Data
public class ReqSetProductDTO {
    @NotBlank
    @ApiModelProperty(value = "판매금액 "   , required = true)
    private int salePrice ;

    @NotBlank
    @ApiModelProperty(value = "공장생산가격 "   , required = true)
    private int originPrice;

    @NotBlank
    @Size(max=50)
    @ApiModelProperty(value = "상품명 "   , required = true)
    private String productName;

    @NotBlank
    @Size(max=4)
    @ApiModelProperty(value = "상품분류(NR : 일반 , EV : 이벤트 )"   , required = true)
    private String productType;

    @NotBlank
    @ApiModelProperty(value = "상품 분류키"   , required = true)
    private Long category;


    @NotBlank
    @ApiModelProperty(value = "이벤트 키"   , required = true)
    private Long eventNum;


}
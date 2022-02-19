package com.example.sample.domain.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.*;

@ApiModel(value = "상품 등록", description = "상품 등록시 사용되는 정보")
@Setter
@Getter
public class ReqSetProductDTO {
    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "판매금액 ", required = true)
    private int salePrice;

    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "공장생산가격 ", required = true)
    private int productPrice;

    @NotBlank
    @ApiModelProperty(value = "상품명 ", required = true)
    private String productName;

    @NotBlank
    @ApiModelProperty(value = "상품분류(NR : 일반 , EV : 이벤트 )", required = true)
    private String productType;

    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "상품 분류키", required = true)
    private Long category;


    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "이벤트 키", required = true)
    private Long eventNum;


}

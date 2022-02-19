package com.example.sample.domain.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SaleProductInfo{

    @NotNull
    @ApiModelProperty(value = "상품정보키"   , required = true)
    private Long productIdx;

    @NotNull
    @ApiModelProperty(value = "구매수량"   , required = true)
    private int productCount;
}

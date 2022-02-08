package com.example.sample.domain.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@ApiModel(value = "상품정보")
@Builder
@Data
public class ResProductDTO {
    @ApiModelProperty(value = "상품 키")
    private Long productIdx;
    @ApiModelProperty(value = "상품 판매가")
    private int salePrice;
    @ApiModelProperty(value = "상품 원가")
    private int providePrice;
    @ApiModelProperty(value = "상품 명")
    private String productName;
    @ApiModelProperty(value = "상품 종류")
    private String productType;
    @ApiModelProperty(value = "상품 카테고리")
    private Long category;
    @ApiModelProperty(value = "이벤트 키")
    private Long eventIdx;
    @ApiModelProperty(value = "상품 등록일")
    private LocalDateTime regDate;
    @ApiModelProperty(value = "상품 수정일")
    private LocalDateTime modifyDate;

    public ResProductDTO(
            Long productIdx,
            int salePrice,
            int providePrice,
            String productName,
            String productType,
            Long category,
            Long eventIdx,
            LocalDateTime regDate,
            LocalDateTime modifyDate
    ){
        this.productIdx     = productIdx  ;
        this.salePrice      = salePrice   ;
        this.providePrice   = providePrice;
        this.productName    = productName ;
        this.productType    = productType ;
        this.category       = category    ;
        this.eventIdx       = eventIdx    ;
        this.regDate        = regDate     ;
        this.modifyDate     = modifyDate  ;
    }

}

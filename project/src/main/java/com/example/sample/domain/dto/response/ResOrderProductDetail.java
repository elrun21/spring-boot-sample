package com.example.sample.domain.dto.response;

import com.example.sample.domain.entity.ProductInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Stream;

@Getter
public class ResOrderProductDetail {
    @ApiModelProperty(value = "상품 갯수")
    private int productCount;

    @ApiModelProperty(value = "상품 가격")
    private int productPrice;

    @ApiModelProperty(value = "상품 구매 가격")
    private int salePrice;

    @ApiModelProperty(value = "상품 총 갯수")
    private int totalPrice;

    @ApiModelProperty(value = "주문 인덱스 키")
    private long orderIdx;

    @ApiModelProperty(value = "상품 키")
    private long productIdx;

    @ApiModelProperty(value = "주문일")
    private LocalDateTime createAt;

    public void setProductIdx(ProductInfo productInfo){
        this.productIdx = productInfo.getIdx();
    }

    @Builder
    public ResOrderProductDetail( int productCount,  int productPrice,  int salePrice,  int totalPrice,  long orderIdx,  long productIdx, LocalDateTime createAt) {
        this.productCount = productCount;
        this.productPrice = productPrice;
        this.salePrice = salePrice;
        this.totalPrice = totalPrice;
        this.orderIdx = orderIdx;
        this.productIdx = productIdx;
        this.createAt = createAt;
    }

}

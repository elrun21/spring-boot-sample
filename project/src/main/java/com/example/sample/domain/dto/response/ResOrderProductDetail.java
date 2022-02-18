package com.example.sample.domain.dto.response;

import com.example.sample.domain.entity.OrderInfo;
import com.example.sample.domain.entity.ProductInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Builder
@Data
public class ResOrderProductDetail {
    @ApiModelProperty(value = "상품 갯수")
    @NonNull
    private int productCount;

    @ApiModelProperty(value = "상품 가격")
    @NonNull
    private int productPrice;

    @ApiModelProperty(value = "상품 구매 가격")
    @NonNull
    private int salePrice;

    @ApiModelProperty(value = "상품 총 갯수")
    @NonNull
    private int totalPrice;

    @ApiModelProperty(value = "주문 인덱스 키")
    @NonNull
    private long orderIdx;

    @ApiModelProperty(value = "상품 키")
    @NonNull
    private long productIdx;

    @ApiModelProperty(value = "주문일")
    private LocalDateTime createAt;

    public void setOrderIdx(OrderInfo orderInfo){
        this.orderIdx = orderInfo.getIdx();
    }
    public void setProductIdx(ProductInfo productInfo){
        this.productIdx = productInfo.getIdx();
    }
}

package com.example.sample.domain.dto.response;

import com.example.sample.domain.entity.OrderInfo;
import com.example.sample.domain.entity.ProductInfo;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ResOrderProductDetail {

    private int productCount;
    private int productPrice;
    private int salePrice;
    private int totalPrice;
    private long orderIdx;
    private long productIdx;
    private LocalDateTime createAt;

    public void setOrderIdx(OrderInfo orderInfo){
        this.orderIdx = orderInfo.getIdx();
    }
    public void setProductIdx(ProductInfo productInfo){
        this.productIdx = productInfo.getIdx();
    }
}

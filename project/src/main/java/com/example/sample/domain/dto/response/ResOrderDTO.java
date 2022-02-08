package com.example.sample.domain.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@ApiModel(value = "주문 정보")
@Builder
@Data
public class ResOrderDTO {
    @ApiModelProperty(value = "주문 번호")
    private String orderNumber;

    @ApiModelProperty(value = "결제 상품명")
    private String productName;

    @ApiModelProperty(value = "결제 금액")
    private int totalPrice;

    @ApiModelProperty(value = "결제 갯수")
    private int totalCount;

    @ApiModelProperty(value = "결제 방법")
    private String paymentType;

    @ApiModelProperty(value = "주문일")
    private LocalDateTime orderDate;

    @ApiModelProperty(value = "구매자")
    private String sender;

    @ApiModelProperty(value = "수령자")
    private String receiver;

    @ApiModelProperty(value = "배송지")
    private String addr;

    public ResOrderDTO(
       String orderNumber,
       String productName,
       int totalPrice,
       int totalCount,
       String paymentType,
       LocalDateTime orderDate,
       String sender,
       String receiver,
       String addr
    ) {
        this.orderNumber = orderNumber;
        this.productName = productName;
        this.totalPrice = totalPrice;
        this.totalCount = totalCount;
        this.paymentType = paymentType;
        this.orderDate = orderDate;
        this.sender = sender;
        this.receiver = receiver;
        this.addr = addr;

    }
}

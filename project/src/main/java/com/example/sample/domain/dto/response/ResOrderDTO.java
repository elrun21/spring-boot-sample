package com.example.sample.domain.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@ApiModel(value = "주문 정보")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResOrderDTO {
    @ApiModelProperty(value = "주문 식별키")
    @NonNull
    private Long orderIdx;

    @ApiModelProperty(value = "주문 번호")
    @NonNull
    private String orderNumber;

    @ApiModelProperty(value = "결제 상품명")
    @NonNull
    private String productName;

    @ApiModelProperty(value = "결제 금액")
    @NonNull
    private int totalPrice;

    @ApiModelProperty(value = "결제 갯수")
    @NonNull
    private int totalCount;

    @ApiModelProperty(value = "결제 방법")
    @NonNull
    private String paymentType;

    @ApiModelProperty(value = "주문일")
    @NonNull
    private LocalDateTime orderDate;

    @ApiModelProperty(value = "구매자")
    @NonNull
    private String sender;

    @ApiModelProperty(value = "수령자")
    @NonNull
    private String receiver;

    @ApiModelProperty(value = "배송지")
    @NonNull
    private String addr;


}

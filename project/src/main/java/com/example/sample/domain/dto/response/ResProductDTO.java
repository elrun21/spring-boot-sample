package com.example.sample.domain.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@ApiModel(value = "상품정보")
@NoArgsConstructor
@Data
public class ResProductDTO {

    @NonNull
    private Long productIdx;

    @ApiModelProperty(value = "상품 판매가")
    @NonNull
    private int salePrice;

    @ApiModelProperty(value = "상품 원가")
    @NonNull
    private int providePrice;

    @ApiModelProperty(value = "상품 명")
    @NonNull
    private String productName;

    @ApiModelProperty(value = "상품 종류")
    @NonNull
    private String productType;

    @ApiModelProperty(value = "상품 카테고리")
    @NonNull
    private Long category;

    @ApiModelProperty(value = "이벤트 키")
    @NonNull
    private Long eventIdx;

    @ApiModelProperty(value = "상품 등록일")
    private LocalDateTime regDate;

    @ApiModelProperty(value = "상품 수정일")
    private LocalDateTime modifyDate;



}

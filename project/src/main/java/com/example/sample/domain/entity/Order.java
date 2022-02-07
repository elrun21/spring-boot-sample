package com.example.sample.domain.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "ORDER")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Order extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ApiModelProperty("주문번호")
    @Column
    private String orderNo;

    @ApiModelProperty("사용자 ID")
    @Column
    private String userId;

    @ApiModelProperty("상품 명")
    @Column
    private String productName;

    @ApiModelProperty("주문수량")
    @Column
    private int productCount;

    @ApiModelProperty("배송지")
    @Column
    private String addr ;

    @ApiModelProperty("수령인")
    @Column
    private String receiver;

    @ApiModelProperty("배송연락처")
    @Column
    private String phone;

    @ApiModelProperty("결제수단")
    @Column
    private String paymentType;

    @ApiModelProperty("지불 금액")
    @Column
    private int paymentPrice;

    @ApiModelProperty("(FK) 회원정보 테이블 키")
    @JoinColumn(name="member_idx")
    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Long userIdx;

    @ApiModelProperty("(FK) 상품 테이블 키")
    @JoinColumn(name="product_idx")
    @ManyToOne( targetEntity = Product.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Long productIdx;


}

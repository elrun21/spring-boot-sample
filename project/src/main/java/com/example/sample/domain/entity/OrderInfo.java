package com.example.sample.domain.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Table(name = "ORDER_INFO")
@Entity
@Getter
@Setter
@DynamicUpdate
@NoArgsConstructor
public class OrderInfo extends BaseTime {
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
    private Member userIdx;

    @ApiModelProperty("(FK) 상품 테이블 키")
    @JoinColumn(name="product_idx")
    @ManyToOne( targetEntity = ProductInfo.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ProductInfo productIdx;

    public static final class OrderBuilder {
        private String orderNo;
        private String userId;
        private String productName;
        private int productCount;
        private String addr ;
        private String receiver;
        private String phone;
        private String paymentType;
        private int paymentPrice;
        private Member userIdx;
        private ProductInfo productIdx;

        private OrderBuilder() {
        }

        public static OrderInfo.OrderBuilder aProduct() {
            return new OrderInfo.OrderBuilder();
        }
        public OrderInfo.OrderBuilder withOrderNo(String orderNo) {
            this.orderNo = orderNo;
            return this;
        }

        public OrderInfo.OrderBuilder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public OrderInfo.OrderBuilder withProductName(String productName) {
            this.productName = productName;
            return this;
        }

        public OrderInfo.OrderBuilder withProductCount(int productCount) {
            this.productCount = productCount;
            return this;
        }
        public OrderInfo.OrderBuilder withAddr(String addr) {
            this.addr = addr;
            return this;
        }
        public OrderInfo.OrderBuilder withReceiver(String receiver) {
            this.receiver = receiver;
            return this;
        }

        public OrderInfo.OrderBuilder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public OrderInfo.OrderBuilder withPaymentType(String paymentType) {
            this.paymentType = paymentType;
            return this;
        }

        public OrderInfo.OrderBuilder withPaymentPrice(int paymentPrice) {
            this.paymentPrice = paymentPrice;
            return this;
        }

        public OrderInfo.OrderBuilder withUserIdx(Member userIdx) {
            this.userIdx = userIdx;
            return this;
        }

        public OrderInfo.OrderBuilder withProductIdx(ProductInfo productIdx) {
            this.productIdx = productIdx;
            return this;
        }
        public OrderInfo build() {
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.orderNo = this.orderNo;
            orderInfo.userId= this.userId;
            orderInfo.productName= this.productName;
            orderInfo.productCount= this.productCount;
            orderInfo.addr = this.addr;
            orderInfo.receiver= this.receiver;
            orderInfo.phone= this.phone;
            orderInfo.paymentType= this.paymentType;
            orderInfo.paymentPrice= this.paymentPrice;
            orderInfo.userIdx= this.userIdx;
            orderInfo.productIdx= this.productIdx;
            return orderInfo;
        }
    }

}

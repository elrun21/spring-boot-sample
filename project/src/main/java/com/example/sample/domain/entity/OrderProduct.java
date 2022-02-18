package com.example.sample.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Table(name = "ORDER_PRODUCT_INFO")
@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor
public class OrderProduct extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @ApiModelProperty("구매수량")
    @Column
    private  int productCount;
    @ApiModelProperty("공장가 가격")
    @Column
    private int productPrice;
    @ApiModelProperty("판매 금액")
    @Column
    private int salePrice;
    @ApiModelProperty("총 금액")
    @Column
    private int totalPrice;


    @ApiModelProperty("(FK) 주문정보 테이블 키")
    @JoinColumn(name="order_idx")
    @ManyToOne(targetEntity = OrderInfo.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private OrderInfo orderIdx;


    @ApiModelProperty("(FK) 상품정보 테이블 키")
    @JoinColumn(name="product_idx")
    @ManyToOne(targetEntity = ProductInfo.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private ProductInfo productIdx;

    public static final class OrderProductBuilder {
        private int productCount;
        private int productPrice;
        private int salePrice;
        private int totalPrice;
        private OrderInfo orderIdx;
        private ProductInfo productIdx;

        private OrderProductBuilder(){

        }

        public static OrderProductBuilder aOrderProduct(){
            return new OrderProductBuilder();
        }

        public OrderProductBuilder withProductCount (int productCount ){
           this.productCount  = productCount;
           return this;
        }
        public OrderProductBuilder withProductPrice (int productPrice ){
            this.productPrice  = productPrice;
            return this;
        }

        public OrderProductBuilder withSalePrice (int salePrice){
            this.salePrice  = salePrice;
            return this;
        }
        public OrderProductBuilder withTotalPrice (int totalPrice){
            this.totalPrice  = totalPrice;
            return this;
        }
        public OrderProductBuilder withProductIdx(ProductInfo productIdx){
            this.productIdx = productIdx;
            return this;
        }


        public OrderProductBuilder withOrderIdx (OrderInfo orderIdx){
            this.orderIdx  = orderIdx;
            return this;
        }
        public OrderProduct build() {
            OrderProduct info = new OrderProduct();
            info.productCount = this.productCount ;
            info.productPrice = this.productPrice ;
            info.salePrice = this.salePrice ;
            info.totalPrice = this.totalPrice ;
            info.orderIdx = this.orderIdx ;
            info.productIdx = this.productIdx;
            return info;
        }
    }
}

package com.example.sample.domain.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Table(name = "PRODUCT_INFO")
@Entity
@Getter
@Setter
@DynamicUpdate
@NoArgsConstructor
public class ProductInfo extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ApiModelProperty("상품 판매가")
    @Column
    private int salePrice;

    @ApiModelProperty("상품 원가")
    @Column
    private int originPrice;

    @ApiModelProperty("상품명")
    @Column
    private String productName;

    @ApiModelProperty("상품종류")
    @Column
    private String productType;

    @ApiModelProperty("상품 카테고리")
    @Column
    private Long category;

    @ApiModelProperty("이벤트 키")
    @Column
    private Long eventNum;

    public static final class ProductBuilder {
        private int salePrice;
        private int productPrice;
        private String productName;
        private String productType;
        private Long category;
        private Long eventNum;

        private ProductBuilder() {
        }

        public static ProductBuilder aProduct() {
            return new ProductBuilder();
        }

        public ProductInfo.ProductBuilder withSalePrice(int salePrice){
            this.salePrice = salePrice;
            return this;
        }

        public ProductInfo.ProductBuilder withProductPrice(int originPrice){
            this.productPrice = originPrice;
            return this;
        }

        public ProductInfo.ProductBuilder withProductName(String productName){
            this.productName = productName;
            return this;
        }

        public ProductInfo.ProductBuilder withProductType(String productType){
            this.productType = productType;
            return this;
        }

        public ProductInfo.ProductBuilder withCategory(Long category){
            this.category = category;
            return this;
        }

        public ProductInfo.ProductBuilder withEventNum(Long eventNum){
            this.eventNum = eventNum;
            return this;
        }


        public ProductInfo build() {
            ProductInfo productInfo = new ProductInfo();
            productInfo.salePrice = this.salePrice;
            productInfo.originPrice = this.productPrice;
            productInfo.productName = this.productName;
            productInfo.productType = this.productType;
            productInfo.category = this.category;
            productInfo.eventNum = this.eventNum;
            return productInfo;
        }
    }
}

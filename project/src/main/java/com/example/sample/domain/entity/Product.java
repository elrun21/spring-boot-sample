package com.example.sample.domain.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "PRODUCT")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
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
        private int originPrice;
        private String productName;
        private String productType;
        private Long category;
        private Long eventNum;

        private ProductBuilder() {
        }

        public static ProductBuilder aProduct() {
            return new ProductBuilder();
        }

        public ProductBuilder withSalePrice(int salePrice){
            this.salePrice = salePrice;
            return this;
        }

        public ProductBuilder withOriginPrice(int originPrice){
            this.originPrice = originPrice;
            return this;
        }

        public ProductBuilder withProductName(String productName){
            this.productName = productName;
            return this;
        }

        public ProductBuilder withProductType(String productType){
            this.productType = productType;
            return this;
        }

        public ProductBuilder withCategory(Long category){
            this.category = category;
            return this;
        }

        public ProductBuilder withEventNum(Long eventNum){
            this.eventNum = eventNum;
            return this;
        }


        public Product build() {
            Product product = new Product();
            product.salePrice = this.salePrice;
            product.originPrice = this.originPrice;
            product.productName = this.productName;
            product.productType = this.productType;
            product.category = this.category;
            product.eventNum = this.eventNum;
            return product;
        }
    }
}

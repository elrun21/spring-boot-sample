package com.example.sample.common.enums;

public enum ResponseCodeEnum {
    MEMBER_NOT_CREATE(201,"Not created User"),
    MEMBER_NOT_USED_EMAIL(202,"Not used Email"),
    MEMBER_INFO_NOT_CREATE(203,"member info is not create"),
    PRODUCT_NOT_CREATE(204,"member info is not create"),
    ORDER_NOT_CREATE_PRODUCT(206,"order parameter error ( Problem! Product IDX or Count )"),
    ORDER_NOT_CREATE_PRODUCT_CNT(205,"order parameter error ( Product Count is Not Null)"),
    ORDER_NOT_CREATE_PRODUCT_IDX(205,"order parameter error ( Product IDX is Not Used)");

    int code;
    String desc;
    ResponseCodeEnum(int code , String desc){
        this.code = code ;
        this.desc = desc;
    }
    public int getCode() { return code;}
    public String getDesc() { return desc;}
}

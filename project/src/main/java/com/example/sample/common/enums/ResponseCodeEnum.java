package com.example.sample.common.enums;

public enum ResponseCodeEnum {
    MEMBER_NOT_CREATE(201,"Not created User"),
    MEMBER_NOT_USED_EMAIL(202,"Not used Email"),
    MEMBER_INFO_NOT_CREATE(203,"member info is not create"),
    PRODUCT_NOT_CREATE(204,"member info is not create");

    int code;
    String desc;
    ResponseCodeEnum(int code , String desc){
        this.code = code ;
        this.desc = desc;
    }
    public int getCode() { return code;}
    public String getDesc() { return desc;}
}

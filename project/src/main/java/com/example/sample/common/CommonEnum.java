package com.example.sample.common;


/**
 * 공통 설정 Enum
 */
public enum CommonEnum {

    COMMON_ENCODE_CHARTER_SET("UTF-8");

    private String value ;
    CommonEnum(String value) {
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}

package com.example.sample.common.enums;

/**
 * 회원 연관 Enum
 */
public enum MemberEnum {
    USER_STATE_LIVE( "LIVE", "사용"),
    USER_STATE_BLOCK("BLOCK", "정지"),
    USER_STATE_DELETE("DELETE", "삭제"),
    ;
    String code ;
    String desc ;
    MemberEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public String getCode() {
        return code ;
    }
}

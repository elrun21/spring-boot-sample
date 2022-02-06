package com.example.sample.domain.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@ApiModel(value = "회원 상세 정보(단건)")
@Data
@Builder
public class ResMemberInfoDTO {
    @ApiModelProperty(value = "사용자 아이디")
    private String userId;
    @ApiModelProperty(value = "사용자 닉네임")
    private String userNickName;
    @ApiModelProperty(value = "사용자 이름")
    private String userName;
    @ApiModelProperty(value = "사용자 전화번호")
    private String userPhone;
    @ApiModelProperty(value = "사용자 주소")
    private String userAddr;
    @ApiModelProperty(value = "사용자 계정 상태 ")
    private String userAccountStatus;
    @ApiModelProperty(value = "사용자 등급")
    private int  userGrade ;
    @ApiModelProperty(value = "사용자 계정 생성큰")
    private LocalDateTime userJoinDate;


}

package com.example.sample.domain.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
@ApiModel(value = "회원 상세 정보 리스트 정보", description = "회원정보를 포함한 리스트를 전달하기 위한 객체")
@Data
public class ResMemberInfosDTO {
    @ApiModelProperty(value = "사용자 내부 인덱스 키")
    private Long userIdx;
    @ApiModelProperty(value = "사용자 아이디")
    private String userId;
    @ApiModelProperty(value = "사용자 이름")
    private String userName;
    @ApiModelProperty(value = "사용자 핸드폰")
    private String userPhone;
    @ApiModelProperty(value = "사용자 주소 ")
    private String userAddr;
    @ApiModelProperty(value = "사용자 계정 상태")
    private String userAccountStatus;
    @ApiModelProperty(value = "사용자 등급")
    private int  userGrade ;
    @ApiModelProperty(value = "사용자 계정 생성일")
    private LocalDateTime userJoinDate;

    public ResMemberInfosDTO(
            Long userIdx,
            String userId,
            String userName,
            String userPhone,
            String userAddr,
            int userGrade,
            String userAccountStatus,
            LocalDateTime userJoinDate
    ){
        this.userIdx = userIdx;
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userAddr = userAddr;
        this.userGrade = userGrade;
        this.userAccountStatus = userAccountStatus;
        this.userJoinDate =userJoinDate;
    }
}

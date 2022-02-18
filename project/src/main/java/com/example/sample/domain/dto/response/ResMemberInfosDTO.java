package com.example.sample.domain.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@ApiModel(value = "회원 상세 정보 리스트 정보", description = "회원정보를 포함한 리스트를 전달하기 위한 객체")
@Data
public class ResMemberInfosDTO {
    @ApiModelProperty(value = "사용자 내부 인덱스 키")
    @NonNull
    private Long userIdx;

    @ApiModelProperty(value = "사용자 아이디")
    @NonNull
    private String userId;

    @ApiModelProperty(value = "사용자 이름")
    @NonNull
    private String userName;

    @ApiModelProperty(value = "사용자 핸드폰")
    @NonNull
    private String userPhone;

    @ApiModelProperty(value = "사용자 주소 ")
    @NonNull
    private String userAddr;

    @ApiModelProperty(value = "사용자 계정 상태")
    @NonNull
    private String userAccountStatus = "LIVE";

    @ApiModelProperty(value = "사용자 등급")
    @NonNull
    private int  userGrade = 0 ;

    @ApiModelProperty(value = "사용자 계정 생성일")
    private LocalDateTime userJoinDate;


}

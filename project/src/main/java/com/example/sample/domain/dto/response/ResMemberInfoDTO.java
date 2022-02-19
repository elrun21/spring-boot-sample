package com.example.sample.domain.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Stream;

@ApiModel(value = "회원 상세 정보(단건)")
@Getter
public class ResMemberInfoDTO  {
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

    @ApiModelProperty(value = "사용자 계정 생성일")
    private LocalDateTime userJoinDate;

    @Builder
    public ResMemberInfoDTO(String userId, String userNickName, String userName, String userPhone, String userAddr, String userAccountStatus, int userGrade, LocalDateTime userJoinDate) {
        this.userId = userId;
        this.userNickName = userNickName;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userAddr = userAddr;
        this.userAccountStatus = userAccountStatus;
        this.userGrade = userGrade;
        this.userJoinDate = userJoinDate;
    }

    public ResMemberInfoDTO validationNullData(){
        boolean check = Stream.of(
                this.getUserId(),
                this.getUserName(),
                this.getUserPhone(),
                this.getUserAddr()
        ).allMatch(Objects::nonNull);
        if ( check ) {
            return this;
        }else {
            return null;
        }
    }
}

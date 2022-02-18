package com.example.sample.domain.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@ApiModel(value = "회원 상세 정보(단건)")
@Data
@Builder
public class ResMemberInfoDTO  {
    @ApiModelProperty(value = "사용자 아이디")
    @NonNull
    private String userId;

    @ApiModelProperty(value = "사용자 닉네임")
    private String userNickName;

    @ApiModelProperty(value = "사용자 이름")
    @NonNull
    private String userName;

    @ApiModelProperty(value = "사용자 전화번호")
    @NonNull
    private String userPhone;

    @ApiModelProperty(value = "사용자 주소")
    @NonNull
    private String userAddr;

    @ApiModelProperty(value = "사용자 계정 상태 ")
    @NonNull
    private String userAccountStatus;

    @ApiModelProperty(value = "사용자 등급")
    @NonNull
    private int  userGrade ;

    @ApiModelProperty(value = "사용자 계정 생성일")
    private LocalDateTime userJoinDate;

    public ResMemberInfoDTO isNull(ResMemberInfoDTO data) {
        if( data == null ){
            return new ResMemberInfoDTO.ResMemberInfoDTOBuilder().build();
        }else{
            return data;
        }
    }
}

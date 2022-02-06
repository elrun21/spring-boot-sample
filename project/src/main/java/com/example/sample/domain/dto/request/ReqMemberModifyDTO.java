package com.example.sample.domain.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@ApiModel(value = "회원 정보 수정", description = "회원 수정시 사용되는 정보")
@Data
public class ReqMemberModifyDTO {
    @ApiModelProperty(value = "로그인 한 아이디", required = true)
    @NotBlank
    @Size(max=20)
    String id ;
    @ApiModelProperty(value = "수정할 핸드폰번호")
    @Size(max=11)
    String phone;
    @ApiModelProperty(value = "수정할 주소 ")
    @Size(max=150)
    String addr;
}

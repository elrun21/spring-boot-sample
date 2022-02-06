package com.example.sample.domain.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel(value = "회원 이메일 로그인", description = "로그인시 사용되는 객체정보")
@Data
public class ReqSignInEmailDTO {
    @NotBlank
    @Size(max=50)
    @ApiModelProperty(value = "로그인 이메일", required = true)
    private  String email ;
    @NotBlank
    @Size(max=20)
    @ApiModelProperty(value = "로그인 패스워드", required = true)
    private  String password;
}

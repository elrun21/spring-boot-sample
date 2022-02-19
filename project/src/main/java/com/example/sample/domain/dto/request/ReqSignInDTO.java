package com.example.sample.domain.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel(value = "회원 로그인", description = "로그인시 사용되는 객체정보")
@Getter
@Setter
public class ReqSignInDTO {
    @NotBlank
    @Size(max=20)
    @ApiModelProperty(value = "로그인 아이디", required = true)
    private  String id ;

    @NotBlank
    @Size(max=20)
    @ApiModelProperty(value = "로그인 패스워드", required = true)
    private  String password;
}

package com.example.sample.domain.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel(value = "회원 가입", description = "회원가입시 사용되는 정보")
@Getter
@Setter
public class ReqSignUpMembeDTO {
    @NotBlank
    @Size(max=20)
    @ApiModelProperty(value = "사용자 아이디(20자 이내) "   , required = true)
    private String id ;

    @NotBlank
    @Size(max=20)
    @ApiModelProperty(value = "사용자 패스워드(20자 이내)", required = true)
    private String password;

    @NotBlank
    @Email
    @Size(max=50)
    @ApiModelProperty(value = "사용자 이메일(50자이내)", required = true)
    private String email;

    @Size(max=11)
    @ApiModelProperty(value = "사용자 핸드폰(11자 이내)")
    private String phone;

    @NotBlank
    @Size(max=20)
    @ApiModelProperty(value = "사용자 이름(20자 이내)" , required = true)
    private String name;

    @Size(max=150)
    @ApiModelProperty(value = "사용자 주소(150자 이내)")
    private String addr;

}

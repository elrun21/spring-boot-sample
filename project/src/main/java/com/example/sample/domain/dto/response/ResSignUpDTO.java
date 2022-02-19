package com.example.sample.domain.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.stream.Stream;

@ApiModel(value = "회원 가입 결과 정보", description = "회원가입후 결과를 전달하기 위한 객체")
@Getter
public class ResSignUpDTO {
    @ApiModelProperty(value = "사용자 내부 인덱스 키")
    private Long userIdx;

    @Builder
    public ResSignUpDTO(Long userIdx) {
        this.userIdx = userIdx;
    }

    public ResSignUpDTO validationNullData(){
        boolean check = Stream.of(
                this.getUserIdx()
        ).allMatch(Objects::nonNull);
        if( check ) {
            return this;
        }else {
            return null;
        }
    }
}

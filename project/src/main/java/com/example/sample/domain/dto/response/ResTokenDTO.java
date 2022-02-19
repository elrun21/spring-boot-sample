package com.example.sample.domain.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.stream.Stream;

@ApiModel(value = "토큰 정보")
@Getter
public class ResTokenDTO {
    @ApiModelProperty(value = "토큰")
    private String token;

    @Builder
    public ResTokenDTO(String token) {
        this.token = token;
    }

    public ResTokenDTO validationNullData(){
        boolean check = Stream.of(
                this.getToken()
        ).allMatch(Objects::nonNull);
        if( check ) {
            return this;
        }else {
            return null;
        }
    }
}

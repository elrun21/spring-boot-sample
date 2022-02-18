package com.example.sample.domain.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@ApiModel(value = "토큰 정보")
@Data
@Builder
public class ResTokenDTO {
    @ApiModelProperty(value = "토큰")
    @NonNull
    private String token;
}

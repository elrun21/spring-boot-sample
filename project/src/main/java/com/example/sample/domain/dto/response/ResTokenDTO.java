package com.example.sample.domain.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
@ApiModel(value = "토큰 정보")
@Data
@Builder
public class ResTokenDTO {
    @ApiModelProperty(value = "토큰")
    private String token;
}

package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("用户tokenDto对象")
public class UserTokenDto implements Serializable {

    @ApiModelProperty("token")
    private String token;
}

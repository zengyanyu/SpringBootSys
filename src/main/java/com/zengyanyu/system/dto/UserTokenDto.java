/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zengyanyu
 */
@Getter
@Setter
@ApiModel("用户tokenDto对象")
public class UserTokenDto implements Serializable {

    @ApiModelProperty("token")
    private String token;
}

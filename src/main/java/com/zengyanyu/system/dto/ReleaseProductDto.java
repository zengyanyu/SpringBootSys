package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("发布产品dto对象")
public class ReleaseProductDto implements Serializable {

    @ApiModelProperty("网关产品ID")
    private String productId;

    @ApiModelProperty("开发状态 {dev：开发中，audit：审核中，released：已发布}")
    private String devStatus;
}

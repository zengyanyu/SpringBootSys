package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("修改产品Dto对象")
public class ModifyStudioProductDto implements Serializable {

    @ApiModelProperty(value = "产品ID", required = true)
    private String productId;

    @ApiModelProperty(value = "产品名称", required = true)
    private String productName;

    @ApiModelProperty(value = "产品描述", required = true)
    private String productDesc;

    @ApiModelProperty(value = "模型ID", required = true)
    private Long moduleId;

    @ApiModelProperty("绑定策略 {1：抢占绑定，2：授权绑定}")
    private Long bindStrategy;

}

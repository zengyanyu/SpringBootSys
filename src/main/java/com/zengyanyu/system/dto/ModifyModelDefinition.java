package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("修改产品数据模板(物模型)")
public class ModifyModelDefinition implements Serializable {

    @ApiModelProperty(value = "模型方案", required = true)
    private String modelSchema;

    @ApiModelProperty(value = "产品ID", required = true)
    private String productId;

    // 是否同步到腾讯云（共享过来的物模型不同步腾讯）
    private Boolean isSyncTencentCloud = Boolean.TRUE;

}

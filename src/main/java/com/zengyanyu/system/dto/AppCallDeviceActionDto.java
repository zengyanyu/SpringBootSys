package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("调用设备行为")
public class AppCallDeviceActionDto implements Serializable {

    @ApiModelProperty(value = "token", required = true)
    private String accessToken;

    @ApiModelProperty(value = "产品ID", required = true)
    private String productId;

    @ApiModelProperty(value = "设备名称", required = true)
    private String deviceName;

    @ApiModelProperty(value = "行为ID", required = true)
    private String actionId;

    @ApiModelProperty("输入参数")
    private String inputParams;

}

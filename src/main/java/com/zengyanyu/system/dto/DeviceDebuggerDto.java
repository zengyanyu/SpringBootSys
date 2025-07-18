package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("设备调试dto对象")
public class DeviceDebuggerDto implements Serializable {

    @ApiModelProperty(value = "产品ID", required = true)
    private String productId;

    @ApiModelProperty(value = "设备名称", required = true)
    private String deviceName;

    @ApiModelProperty("行为ID")
    private String actionId;

    @ApiModelProperty("行为调用_输入参数")
    private String inputParams;

    @ApiModelProperty("属性调试_参数")
    private String data;
}

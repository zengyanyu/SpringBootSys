package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("获取设备物模型数据Dto对象")
public class AppGetDeviceDataDto implements Serializable {

    @ApiModelProperty(value = "token", required = true)
    private String accessToken;

    @ApiModelProperty(value = "产品ID", required = true)
    private String productId;

    @ApiModelProperty(value = "设备名称", required = true)
    private String deviceName;
}

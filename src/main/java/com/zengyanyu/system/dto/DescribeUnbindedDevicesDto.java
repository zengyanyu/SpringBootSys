package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("获取未绑定的设备列表dto对象")
public class DescribeUnbindedDevicesDto implements Serializable {

    @ApiModelProperty("产品ID")
    private String productId;

    @ApiModelProperty("设备名称")
    private String deviceName;

}

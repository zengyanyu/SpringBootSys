package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("子设备dto对象")
public class SubDeviceDto implements Serializable {

    @ApiModelProperty("产品ID")
    private String productId;

    @ApiModelProperty("子设备名称")
    private String deviceName;

    @ApiModelProperty("设备所属产品")
    private String productName;

    @ApiModelProperty("网关设备名称")
    private String gatewayDeviceName;

    @ApiModelProperty("最后上线时间")
    private LocalDateTime LoginTime;

    @ApiModelProperty("状态中文名称")
    private String statusName;
}

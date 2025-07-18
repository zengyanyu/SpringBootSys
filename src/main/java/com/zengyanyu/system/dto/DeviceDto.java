package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("设备dto对象")
public class DeviceDto implements Serializable {

    @ApiModelProperty("设备名称")
    private String deviceName;

    @ApiModelProperty("产品ID")
    private String productId;

    @ApiModelProperty("所属产品")
    private String productName;

    @ApiModelProperty("设备秘钥")
    private String devicePsk;

    @ApiModelProperty("设备创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("最后上线时间")
    private LocalDateTime LoginTime;

    @ApiModelProperty("激活时间")
    private LocalDateTime firstOnlineTime;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("状态中文名称")
    private String statusName;

    @ApiModelProperty("固件版本")
    private String version;
}

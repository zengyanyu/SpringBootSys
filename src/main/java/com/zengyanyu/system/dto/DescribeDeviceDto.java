package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("设备Dto对象")
public class DescribeDeviceDto implements Serializable {

    @ApiModelProperty("产品ID")
    private String productId;

    @ApiModelProperty("设备名称")
    private String deviceName;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("状态中文名称")
    private String statusName;

    @ApiModelProperty("设备类型")
    private String deviceType;

}

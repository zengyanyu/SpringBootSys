package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("获取设备激活详情Dto对象")
public class DescribeActivateDeviceDto implements Serializable {

    @ApiModelProperty("实例设备数上限")
    private Long totalDeviceNum;

    @ApiModelProperty("已注册设备数")
    private Long usedDeviceNum;
}

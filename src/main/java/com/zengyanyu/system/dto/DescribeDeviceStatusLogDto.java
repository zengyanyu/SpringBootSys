package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("获取设备上下线日志Dto对象")
public class DescribeDeviceStatusLogDto implements Serializable {

    @ApiModelProperty(value = "最小时间", required = true)
    private Long minTime;

    @ApiModelProperty(value = "最大时间", required = true)
    private Long maxTime;

    @ApiModelProperty(value = "产品ID", required = true)
    private String productId;

    @ApiModelProperty(value = "设备名称", required = true)
    private String deviceName;

}

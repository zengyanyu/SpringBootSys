package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("设备历史数据Dto对象")
public class DeviceDataHistoryDto implements Serializable {

    @ApiModelProperty("时间")
    private LocalDateTime time;

    @ApiModelProperty("变量值")
    private String value;
}

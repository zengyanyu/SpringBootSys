package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("设备上下线日志Dto对象")
public class DeviceStatusLogDto implements Serializable {

    @ApiModelProperty("时间")
    private LocalDateTime time;

    @ApiModelProperty("动作")
    private String type;

    @ApiModelProperty("详细信息")
    private String data;
}

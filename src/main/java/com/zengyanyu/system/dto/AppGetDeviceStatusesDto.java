package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("获取设备当前状态")
public class AppGetDeviceStatusesDto implements Serializable {

    @ApiModelProperty(value = "token", required = true)
    private String accessToken;

    @ApiModelProperty(value = "设备ID集合", required = true)
    private String[] deviceIds;
}

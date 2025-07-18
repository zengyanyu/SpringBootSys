package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("事件历史Dto对象")
public class EventHistoryDto implements Serializable {

    @ApiModelProperty("时间")
    private Long timeStamp;

    @ApiModelProperty(value = "产品ID", required = true)
    private String productId;

    @ApiModelProperty(value = "设备名称", required = true)
    private String deviceName;

    @ApiModelProperty("事件ID")
    private String eventId;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("数据")
    private String data;
}

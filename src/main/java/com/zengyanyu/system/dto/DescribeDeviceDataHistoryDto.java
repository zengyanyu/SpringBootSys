package com.zengyanyu.system.dto;

import com.zengyanyu.system.util.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@ApiModel("设备历史数据")
public class DescribeDeviceDataHistoryDto implements Serializable {

    // 查询前两天的开始时间
    @ApiModelProperty(value = "最小时间", required = true)
    private Long minTime = DateUtils.getDateTimeToLong(DateUtils.getStartTime().minus(2, ChronoUnit.DAYS));

    @ApiModelProperty(value = "最大时间", required = true)
    private Long maxTime = DateUtils.getDateTimeToLong(DateUtils.getEndTime());

    @ApiModelProperty(value = "产品ID", required = true)
    private String productId;

    @ApiModelProperty(value = "设备名称", required = true)
    private String deviceName;

    @ApiModelProperty(value = "属性字段名称，对应标识符", required = true)
    private String fieldName;

    @ApiModelProperty("上下文")
    private String context;
}

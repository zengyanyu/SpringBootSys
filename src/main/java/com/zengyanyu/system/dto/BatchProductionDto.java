package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("量产产品dto对象")
public class BatchProductionDto implements Serializable {

    @ApiModelProperty("批次")
    private String batchProductionId;

    @ApiModelProperty("产品ID")
    private String productId;

    @ApiModelProperty("烧录方式")
    private Long burnMethod;

    @ApiModelProperty("烧录方式名称")
    private String burnMethodName;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("产品名称")
    private String productName;

    @ApiModelProperty("创建时间")
    private LocalDateTime createDateTime;
}

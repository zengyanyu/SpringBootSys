package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("创建量产任务")
public class CreateBatchProductionDto implements Serializable {

    @ApiModelProperty("产品ID")
    private String productId;

    @ApiModelProperty("产品名称")
    private String productName;

    @ApiModelProperty("烧录方式")
    private Long burnMethod;

    @ApiModelProperty("生成方式")
    private Long generationMethod;

    @ApiModelProperty("上传地址")
    private String uploadUrl;

    @ApiModelProperty("批次总数")
    private Long batchCnt;

}

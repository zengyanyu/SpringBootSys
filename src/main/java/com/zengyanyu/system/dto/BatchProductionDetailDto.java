package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("量产产品详情dto对象")
public class BatchProductionDetailDto implements Serializable {

    @ApiModelProperty("批次总数")
    private Long batchCnt;

    @ApiModelProperty("烧录方式")
    private Long burnMethod;

    @ApiModelProperty("烧录方式名称")
    private String burnMethodName;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("创建时间")
    private LocalDateTime createDateTime;

    @ApiModelProperty("下载地址")
    private String downloadUrl;

    @ApiModelProperty("生成方式")
    private Long generationMethod;

    @ApiModelProperty("生成方式名称")
    private String generationMethodName;

    @ApiModelProperty("上传地址")
    private String uploadUrl;
}

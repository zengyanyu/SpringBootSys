package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("物模型日志属性Dto")
public class ModelTemplateLogDto implements Serializable {

    @ApiModelProperty("标识符")
    private String code;

    @ApiModelProperty("更新时间")
    private LocalDateTime lastUpdate;

    @ApiModelProperty("最新值")
    private String value;
}

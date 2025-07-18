package com.zengyanyu.system.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("通用公共实体")
public class BaseEntity implements Serializable {

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更改人")
    private String updateBy;

    @ApiModelProperty("更改时间")
    private LocalDateTime updateTime;
}

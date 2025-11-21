package com.zengyanyu.system.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@ApiModel("垃圾查询对象")
public class RubbishQueryObject extends QueryObject {

    @ApiModelProperty("名称")
    private String name;
}

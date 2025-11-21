package com.zengyanyu.system.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@ApiModel("海滩垃圾查询对象")
public class RubbishBeachQueryObject extends QueryObject {

    /**
     * 海滩名称
     */
    @ApiModelProperty("海滩名称")
    private String beachName;
}

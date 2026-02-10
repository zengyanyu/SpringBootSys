package com.zengyanyu.system.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zengyanyu
 */
@Getter
@Setter
@ToString(callSuper = true)
@ApiModel("字典查询对象")
public class DictQueryObject extends QueryObject {

    @ApiModelProperty("字典名称")
    private String name;
}

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
@ApiModel("字典项目查询对象")
public class DictItemQueryObject extends QueryObject {

    @ApiModelProperty("字典项名称")
    private String name;
}

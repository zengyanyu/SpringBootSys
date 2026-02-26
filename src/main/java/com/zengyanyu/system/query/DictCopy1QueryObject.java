package com.zengyanyu.system.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 字典Copy对象查询对象
 *
 * @author zengyanyu
 */
@Getter
@Setter
@ToString(callSuper = true)
@ApiModel("字典Copy对象查询对象")
public class DictCopy1QueryObject extends QueryObject {

    @ApiModelProperty("名称")
    private String name;

}

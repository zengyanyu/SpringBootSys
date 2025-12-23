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
@ApiModel("用户查询对象")
public class UserQueryObject extends QueryObject {

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("企业ID")
    private String compId;
}

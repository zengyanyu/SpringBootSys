package com.zengyanyu.system.query;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zengyanyu
 */
@Getter
@Setter
@ToString(callSuper = true)
@ApiModel("用户关联的角色查询对象")
public class UserRoleQueryObject extends QueryObject{
}

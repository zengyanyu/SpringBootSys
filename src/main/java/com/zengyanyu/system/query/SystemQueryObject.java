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
@ApiModel("系统查询对象")
public class SystemQueryObject extends QueryObject {
}

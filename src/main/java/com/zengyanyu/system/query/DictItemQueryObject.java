package com.zengyanyu.system.query;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@ApiModel("字典项目查询对象")
public class DictItemQueryObject extends QueryObject {
}

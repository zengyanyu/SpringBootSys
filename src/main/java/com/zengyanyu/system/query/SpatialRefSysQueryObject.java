package com.zengyanyu.system.query;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@ApiModel("查询对象")
public class SpatialRefSysQueryObject extends QueryObject{
}

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
@ApiModel("权限记录查询对象")
public class PermissionRecordQueryObject extends QueryObject {

    @ApiModelProperty("方法名称")
    private String methodName;

    @ApiModelProperty("API操作名称")
    private String apiOperationName;
}

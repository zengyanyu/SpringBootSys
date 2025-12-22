package com.zengyanyu.system.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zengyanyu
 */
@Getter
@Setter
@ApiModel("权限记录导出Excel文件Dto对象")
public class PermissionRecordExportExcelDto implements Serializable {

    @ExcelProperty(value = "方法名称", index = 0)
    @ApiModelProperty("方法名称")
    private String methodName;

    @ExcelProperty(value = "API操作名称", index = 1)
    @ApiModelProperty("API操作名称")
    private String apiOperationName;

    @ExcelProperty(value = "路径", index = 2)
    @ApiModelProperty("路径")
    private String path;

    @ExcelProperty(value = "请求方法", index = 3)
    @ApiModelProperty("请求方法")
    private String requestMethod;
}

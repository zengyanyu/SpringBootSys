package com.zengyanyu.system.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 日志记录导出ExcelDto对象
 *
 * @author zengyanyu
 */
@Getter
@Setter
@ApiModel("日志记录导出ExcelDto对象")
public class LogRecordExportExcelDto implements Serializable {

    @ExcelProperty(value = "请求入参", index = 0)
    @ApiModelProperty("请求入参")
    private String inputParam;

    @ExcelProperty(value = "请求方式", index = 1)
    @ApiModelProperty("请求方式")
    private String method;

    @ExcelProperty(value = "操作名称", index = 2)
    @ApiModelProperty("操作名称")
    private String operateName;

    @ExcelProperty(value = "操作账号", index = 3)
    @ApiModelProperty("操作账号")
    private String operateUsername;

    @ExcelProperty(value = "请求出参", index = 4)
    @ApiModelProperty("请求出参")
    private String outputParam;

    @ExcelProperty(value = "请求路径", index = 5)
    @ApiModelProperty("请求路径")
    private String path;

    @ExcelProperty(value = "全限定类名称", index = 6)
    @ApiModelProperty("全限定类名称")
    private String qualifiedName;

    @ExcelProperty(value = "请求IP地址", index = 7)
    @ApiModelProperty("请求IP地址")
    private String requestIp;

    @ExcelProperty(value = "请求开始时间", index = 8)
    @ApiModelProperty("请求开始时间")
    private LocalDateTime requestTime;

    @ExcelProperty(value = "请求响应时间", index = 9)
    @ApiModelProperty("请求响应时间")
    private LocalDateTime responseTime;

    @ExcelProperty(value = "请求是否成功", index = 10)
    @ApiModelProperty("请求是否成功")
    private String status;
}

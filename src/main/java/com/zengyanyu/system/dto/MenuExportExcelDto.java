package com.zengyanyu.system.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 菜单导出ExcelDto对象
 *
 * @author zengyanyu
 */
@Getter
@Setter
@ToString
@ApiModel("菜单导出ExcelDto对象")
public class MenuExportExcelDto implements Serializable {

    @ExcelProperty(value = "菜单名称", index = 0)
    @ApiModelProperty("菜单名称")
    private String name;

    @ExcelProperty(value = "菜单路径", index = 1)
    @ApiModelProperty("菜单路径")
    private String path;
}

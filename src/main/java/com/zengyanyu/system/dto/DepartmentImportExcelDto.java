package com.zengyanyu.system.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 部门导入ExcelDto对象
 *
 * @author zengyanyu
 */
@Getter
@Setter
@ToString
@ApiModel("部门导入ExcelDto对象")
public class DepartmentImportExcelDto implements Serializable {

    @ExcelProperty(value = "字典编码", index = 0)
    @ApiModelProperty("部门名称")
    private String deptName;
}

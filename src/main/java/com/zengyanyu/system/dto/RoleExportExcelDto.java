package com.zengyanyu.system.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 角色导出ExcelDto对象
 *
 * @author zengyanyu
 */
@Getter
@Setter
@ToString
@ApiModel("角色导出ExcelDto对象")
public class RoleExportExcelDto implements Serializable {

    @ExcelProperty(value = "角色编码", index = 0)
    @ApiModelProperty("角色编码")
    private String roleCode;

    @ExcelProperty(value = "角色名称", index = 1)
    @ApiModelProperty("角色名称")
    private String roleName;
}

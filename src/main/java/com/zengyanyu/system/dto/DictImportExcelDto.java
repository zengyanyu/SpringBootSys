package com.zengyanyu.system.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 字典导入ExcelDto对象
 *
 * @author zengyanyu
 */
@Getter
@Setter
@ToString
@ApiModel("字典导入ExcelDto对象")
public class DictImportExcelDto implements Serializable {

    @ExcelProperty(value = "字典编码", index = 0)
    @ApiModelProperty("字典编码")
    private String code;

    @ExcelProperty(value = "字典名称", index = 1)
    @ApiModelProperty("字典名称")
    private String name;
}

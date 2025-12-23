package com.zengyanyu.system.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.zengyanyu.system.converter.DateConverter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zengyanyu
 */
@Getter
@Setter
@ApiModel("字典导出Excel文件Dto对象")
public class DictExportExcelDto implements Serializable {

    @ExcelProperty(value = "字典编码", index = 0)
    @ApiModelProperty("字典编码")
    private String code;

    @ExcelProperty(value = "字典名称", index = 1)
    @ApiModelProperty("字典名称")
    private String name;

    @ExcelProperty(value = "导出日期", index = 2, converter = DateConverter.class)
    @ApiModelProperty("导出日期")
    private Date exportDate;
}

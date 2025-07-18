package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("物模型Dto对象")
public class ModelTemplateDto implements Serializable {

    @ApiModelProperty("物模型编码")
    private String code;

    @ApiModelProperty("物模型名称")
    private String name;

    @ApiModelProperty("数据类型")
    private String type;

    @ApiModelProperty("数据类型名称")
    private String typeName;

    @ApiModelProperty("更新时间")
    private LocalDateTime lastUpdate;

    @ApiModelProperty("最新值")
    private String value;

    public static String getTypeNameByType(String type) {
        String typeName = "";
        switch (type) {
            case "string":
                typeName = "字符串";
                break;
            case "bool":
                typeName = "布尔型";
                break;
            case "int":
                typeName = "整数型";
                break;
            case "float":
                typeName = "浮点型";
                break;
            case "enum":
                typeName = "枚举整型";
                break;
            case "stringenum":
                typeName = "枚举字符串";
                break;
            case "timestamp":
                typeName = "时间型";
                break;
        }
        return typeName;
    }
}

package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("分类名称Dto对象")
public class CategoryNameDto implements Serializable {

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("分类ID")
    private String categoryId;

    @ApiModelProperty("分类图标")
    private String iconUrl;
}

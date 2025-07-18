package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ApiModel("产品品类tree状结构dto对象")
public class CategoryDetailTreeDto implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("分类名称")
    private String label;

    @ApiModelProperty("分类排序")
    private Long listOrder;

    @ApiModelProperty("品类子分类")
    private List<CategoryDetailTreeDto> children;
}

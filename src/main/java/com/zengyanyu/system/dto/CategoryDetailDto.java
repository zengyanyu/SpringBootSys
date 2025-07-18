package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ApiModel("产品品类Dto对象")
public class CategoryDetailDto implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("父ID")
    private String parentId;

    @ApiModelProperty("产品品类名称")
    private String categoryName;

    @ApiModelProperty("分类排序")
    private Long listOrder;

    @ApiModelProperty("分类图标")
    private String iconUrl;

    @ApiModelProperty("0：无，1：免，开发面板")
    private Boolean isDefaultUi;

    @ApiModelProperty("0：无，1：标，物模型")
    private Boolean isDefaultModel;

    @ApiModelProperty("产品品类")
    private List<CategoryDetailDto> categoryDetailDtos;
}

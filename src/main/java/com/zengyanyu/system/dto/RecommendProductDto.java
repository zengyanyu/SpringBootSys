package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("推荐产品Dto对象")
public class RecommendProductDto implements Serializable {

    @ApiModelProperty("产品ID")
    private String productId;

    @ApiModelProperty("产品名称")
    private String productName;

    @ApiModelProperty("产品分类ID")
    private Long categoryId;

    @ApiModelProperty("产品展示图标（文件名称）")
    private String fileName;
}

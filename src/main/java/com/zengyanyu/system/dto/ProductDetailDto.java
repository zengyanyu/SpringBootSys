package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("产品详情Dto对象")
public class ProductDetailDto implements Serializable {

    @ApiModelProperty("产品ID")
    private String productId;

    @ApiModelProperty("文本")
    private String desc;

    @ApiModelProperty("购买地址")
    private String purchaseUrl;
}

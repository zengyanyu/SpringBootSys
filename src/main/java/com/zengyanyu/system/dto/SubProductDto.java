package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("子产品dto对象")
public class SubProductDto implements Serializable {

    @ApiModelProperty("网关产品ID")
    private String gatewayProductId;

    @ApiModelProperty("子产品ID集合")
    private String[] productIds;
}

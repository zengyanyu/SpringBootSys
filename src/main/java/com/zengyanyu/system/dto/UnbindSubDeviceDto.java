package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("解绑子设备Dto对象")
public class UnbindSubDeviceDto implements Serializable {

    @ApiModelProperty(value = "网关产品ID", required = true)
    private String gatewayProductId;

    @ApiModelProperty(value = "网关设备名称", required = true)
    private String gatewayDeviceName;

    @ApiModelProperty(value = "子产品ID", required = true)
    private String productId;

    @ApiModelProperty(value = "设备名称集合", required = true)
    private String[] deviceNames;
}

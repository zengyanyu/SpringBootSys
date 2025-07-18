package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("产品展示配置dto对象")
public class ProductShowConfigDto implements Serializable {

    @ApiModelProperty("主键ID")
    private String productId;

    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("产品展示名称")
    private String productShowName;

    @ApiModelProperty("厂家名称")
    private String companyName;

    @ApiModelProperty("产品型号")
    private String productModel;

    @ApiModelProperty("面板类型{标准面板：standardPanel，H5自定义面板：h5Panel}")
    private String panelType;

}

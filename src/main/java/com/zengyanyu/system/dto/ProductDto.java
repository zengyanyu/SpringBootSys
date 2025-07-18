package com.zengyanyu.system.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel("产品Dto对象")
public class ProductDto implements Serializable {

    @ApiModelProperty("产品ID")
    private String productId;

    @ApiModelProperty("分类ID")
    private int categoryId;

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("开发状态")
    private String devStatus;

    @ApiModelProperty("开发状态中文名称")
    private String devStatusName;

    @ApiModelProperty("数据协议")
    private int dataProtocol;

    @ApiModelProperty("产品名称")
    private String productName;

    @ApiModelProperty("productOwnerName")
    private String productOwnerName;

    @ApiModelProperty("项目ID")
    private String projectId;

    @ApiModelProperty("产品类型")
    private int productType;

    @ApiModelProperty("产品类型中文名称")
    private String productTypeName;

    @ApiModelProperty("通讯方式")
    private String netType;

}

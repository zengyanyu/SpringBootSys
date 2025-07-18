package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("产品JSON对象Dto")
public class ProductJSONObjectDto implements Serializable {

    @ApiModelProperty("分类ID")
    private Long categoryId;

    @ApiModelProperty("设备类型")
    private String encryptionType;

    @ApiModelProperty("产品名称")
    private String productName;

    @ApiModelProperty("设备类型")
    private Long productType;

    @ApiModelProperty("设备类型中文名称")
    private String productTypeName;

    @ApiModelProperty("项目ID")
    private String projectId;

    @ApiModelProperty("产品ID")
    private String productId;

    @ApiModelProperty("创建用户ID")
    private Long createUserId;

    /* dev  audit  released*/
    @ApiModelProperty("状态")
    private String devStatus;

    @ApiModelProperty("状态中文名称")
    private String devStatusName;

    private Long moduleId;

    @ApiModelProperty("数据协议")
    private Long dataProtocol;

    private String enableProductScript;

    @ApiModelProperty("产品描述")
    private String productDesc;

    @ApiModelProperty("区域")
    private String region;

    @ApiModelProperty("创建人昵称")
    private String creatorNickName;

    @ApiModelProperty("通讯方式")
    private String netType;

    @ApiModelProperty("绑定策略")
    private Long bindStrategy;

    @ApiModelProperty("设备总数")
    private Long deviceCount;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("修改时间")
    private Long updateTime;
}

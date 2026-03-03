/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zengyanyu
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("文件dto对象")
public class FileDto implements Serializable {

    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("上传文件名称")
    private String oldFilename;

}

/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 部门管理
 *
 * @author zengyanyu
 */
@Getter
@Setter
@Entity
@TableName("department")
@ApiModel(value = "部门管理", description = "部门管理")
public class Department extends BaseEntity {

    @Id
    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("部门名称")
    private String deptName;

}

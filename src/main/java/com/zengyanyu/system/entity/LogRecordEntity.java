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
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 日志记录
 *
 * @author zengyanyu
 */
@Getter
@Setter
@Entity
@TableName("log_record_entity")
@ApiModel(value = "日志记录", description = "日志记录")
public class LogRecordEntity implements Serializable {

    @Id
    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("异常信息")
    private String errorMsg;

    @ApiModelProperty("请求入参")
    private String inputParam;

    @ApiModelProperty("请求方式")
    private String method;

    @ApiModelProperty("操作名称")
    private String operateName;

    @ApiModelProperty("操作账号")
    private String operateUsername;

    @ApiModelProperty("请求出参")
    private String outputParam;

    @ApiModelProperty("请求路径")
    private String path;

    @ApiModelProperty("全限定类名称")
    private String qualifiedName;

    @ApiModelProperty("请求IP地址")
    private String requestIp;

    @ApiModelProperty("请求时间")
    private LocalDateTime requestTime;

    @ApiModelProperty("响应时间")
    private LocalDateTime responseTime;

    @ApiModelProperty("请求是否成功")
    private String status;

}

package com.zengyanyu.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 权限记录
 *
 * @author zengyanyu
 * @since 2025-07-18
 */
@Getter
@Setter
@Entity
@TableName("permission_record")
@ApiModel(value = "权限记录", description = "权限记录")
public class PermissionRecord implements Serializable {

    @Id
    private String id;

    @ApiModelProperty("方法名称")
    private String methodName;

    @ApiModelProperty("API操作名称")
    private String apiOperationName;

    @ApiModelProperty("路径")
    private String path;

    @ApiModelProperty("请求方法")
    private String requestMethod;

}

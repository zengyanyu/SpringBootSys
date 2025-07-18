package com.zengyanyu.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 *
 *
 * @author zengyanyu
 * @since 2025-07-18
 */
@Getter
@Setter
@Entity
@TableName("permission_record")
@ApiModel(value = "PermissionRecord对象", description = "")
public class PermissionRecord implements Serializable {

    @Id
    private String id;

    private String methodName;

    private String apiOperationName;

    private String path;

    private String requestMethod;


}

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
 * 角色
 *
 * @author zengyanyu
 * @since 2024-11-19
 */
@Getter
@Setter
@Entity
@TableName("role")
@ApiModel(value = "角色", description = "角色")
public class Role implements Serializable {

    @Id
    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色编码")
    private String roleCode;

}

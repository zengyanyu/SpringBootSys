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
 * 菜单
 *
 * @author zengyanyu
 * @since 2025-07-18
 */
@Getter
@Setter
@Entity
@TableName("menu")
@ApiModel(value = "菜单", description = "菜单")
public class Menu extends BaseEntity {

    @ApiModelProperty("菜单名称")
    private String name;

    @ApiModelProperty("菜单路径")
    private String path;

}

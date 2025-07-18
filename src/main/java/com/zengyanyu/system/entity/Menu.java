package com.zengyanyu.system.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * 菜单
 *
 * @author zengyanyu
 * @since 2025-07-16
 */
@Getter
@Setter
@Entity
@TableName("menu")
@ApiModel(value = "Menu对象", description = "菜单")
public class Menu implements Serializable {

    @Id
    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("菜单名称")
    private String name;

    @ApiModelProperty("菜单路径")
    private String path;


}

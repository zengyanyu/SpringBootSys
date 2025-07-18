package com.zengyanyu.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 数据字典
 *
 * @author zengyanyu
 * @since 2024-11-18
 */
@Getter
@Setter
@Entity
@TableName("dict")
@ApiModel(value = "数据字典", description = "数据字典")
public class Dict extends BaseEntity {

    @Id
    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("字典编码")
    private String code;

    @ApiModelProperty("字典名称")
    private String name;

}

package com.zengyanyu.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 数据字典项
 *
 * @author zengyanyu
 * @since 2024-11-18
 */
@Getter
@Setter
@Entity
@TableName("dict_item")
@ApiModel(value = "数据字典项", description = "数据字典项")
public class DictItem extends BaseEntity {

    @Id
    @ApiModelProperty("主键项ID")
    private String id;

    @ApiModelProperty("字典外键ID")
    private String dictId;

    @ApiModelProperty("字典项编码")
    private String code;

    @ApiModelProperty("字典项名称")
    private String name;

}

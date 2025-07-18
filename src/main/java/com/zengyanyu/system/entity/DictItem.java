package com.zengyanyu.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * 数据字典项
 *
 * @author zengyanyu
 * @since 2025-07-18
 */
@Getter
@Setter
@Entity
@TableName("dict_item")
@ApiModel(value = "DictItem对象", description = "数据字典项")
public class DictItem implements Serializable {

    @Id
    private String id;

    private String dictId;

    @ApiModelProperty("字典项编码")
    private String code;

    @ApiModelProperty("字典项名称")
    private String name;


}

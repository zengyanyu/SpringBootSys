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
 * 数据字典
 *
 * @author zengyanyu
 * @since 2025-07-18
 */
@Getter
@Setter
@Entity
@TableName("dict")
@ApiModel(value = "Dict对象", description = "数据字典")
public class Dict implements Serializable {

    @Id
    private String id;

    @ApiModelProperty("字典编码")
    private String code;

    private String name;


}

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
 * 垃圾
 *
 * @author zengyanyu
 * @since 2025-11-21
 */
@Getter
@Setter
@Entity
@TableName("rubbish")
@ApiModel(value = "Rubbish对象", description = "垃圾")
public class Rubbish implements Serializable {

    @Id
    @ApiModelProperty("主键ID")
    private Integer id;

    @ApiModelProperty("名称")
    private String name;

}

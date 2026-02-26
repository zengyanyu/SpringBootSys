package com.zengyanyu.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 *
 * @author zengyanyu
 * @since 2026-02-26
 */
@Getter
@Setter
@Entity
@TableName("dict_copy1")
@ApiModel(value = "DictCopy1对象", description = "")
public class DictCopy1 implements Serializable {

    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    private String code;

    private String name;


}

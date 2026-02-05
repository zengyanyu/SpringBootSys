package com.zengyanyu.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 字典Copy对象
 *
 * @author zengyanyu
 * @since 2026-02-05
 */
@Getter
@Setter
@Entity
@TableName("dict_copy1")
@ApiModel(value = "DictCopy1对象", description = "字典Copy对象")
public class DictCopy1 implements Serializable {

    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 字典编码
     */
    @ApiModelProperty("字典编码")
    private String code;

    /**
     * 字典名称
     */
    @ApiModelProperty("字典名称")
    private String name;


}

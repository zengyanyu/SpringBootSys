package com.zengyanyu.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author zengyanyu
 * @since 2026-02-26
 */
@Getter
@Setter
@Entity
@TableName("dict_copy1")
@ApiModel(value = "DictCopy1对象", description = "")
public class DictCopy1 extends BaseEntity {

    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    private String code;

    private String name;

}

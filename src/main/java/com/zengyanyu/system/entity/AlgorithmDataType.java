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
 * 算法类型
 *
 * @author zengyanyu
 * @since 2025-11-19
 */
@Getter
@Setter
@Entity
@TableName("algorithm_data_type")
@ApiModel(value = "AlgorithmDataType对象", description = "算法类型")
public class AlgorithmDataType implements Serializable {

    @Id
    @ApiModelProperty("id")
    private Integer id;

    @Id
    @ApiModelProperty("算法类型")
    private String type;

}

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
 * 算法要素对应关系表
 *
 * @author zengyanyu
 * @since 2025-11-19
 */
@Getter
@Setter
@Entity
@TableName("algorithm_data_factor_save")
@ApiModel(value = "AlgorithmDataFactorSave对象", description = "算法要素对应关系表")
public class AlgorithmDataFactorSave implements Serializable {

    @Id
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("要素对应的算法")
    private Integer dataId;

    @ApiModelProperty("算法对应的要素")
    private Integer factorId;

}

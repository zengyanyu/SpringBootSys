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
 * 算法要素
 *
 * @author zengyanyu
 * @since 2025-11-19
 */
@Getter
@Setter
@Entity
@TableName("algorithm_data_factor")
@ApiModel(value = "AlgorithmDataFactor对象", description = "算法要素")
public class AlgorithmDataFactor implements Serializable {

    @Id
    @ApiModelProperty("id")
    private Integer id;

    @Id
    @ApiModelProperty("要素对应的环境算法")
    private Integer envType;

    @ApiModelProperty("要素名称")
    private String factor;

}

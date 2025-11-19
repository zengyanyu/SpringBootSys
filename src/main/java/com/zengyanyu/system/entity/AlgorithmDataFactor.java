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

    @ApiModelProperty("要素对应的环境算法")
    private Integer envType;

    @ApiModelProperty("要素名称")
    private String factor;

}

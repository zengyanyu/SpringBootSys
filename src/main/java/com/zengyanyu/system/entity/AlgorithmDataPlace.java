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
 * 算法平台
 *
 * @author zengyanyu
 * @since 2025-11-19
 */
@Getter
@Setter
@Entity
@TableName("algorithm_data_place")
@ApiModel(value = "AlgorithmDataPlace对象", description = "算法平台")
public class AlgorithmDataPlace implements Serializable {

    @Id
    @ApiModelProperty("id")
    private Integer id;

    @Id
    @ApiModelProperty("算法平台")
    private String place;

}

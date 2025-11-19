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
 * 算法数据
 *
 * @author zengyanyu
 * @since 2025-11-19
 */
@Getter
@Setter
@Entity
@TableName("algorithm_data")
@ApiModel(value = "AlgorithmData对象", description = "算法数据")
public class AlgorithmData implements Serializable {

    @Id
    @ApiModelProperty("id")
    private Integer id;

    @Id
    @ApiModelProperty("算法环境类型")
    private Integer algorithmType;

    @ApiModelProperty("算法描述")
    private String describ;

    @ApiModelProperty("算法语言")
    private Integer language;

    @ApiModelProperty("算法名称")
    private String name;

    @ApiModelProperty("算法单位")
    private Integer place;

    @ApiModelProperty("算法类型")
    private Integer type;

    @ApiModelProperty("算法版本")
    private String version;

}

package com.zengyanyu.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 海滩垃圾
 *
 * @author zengyanyu
 * @since 2025-11-21
 */
@Getter
@Setter
@Entity
@TableName("rubbish_beach")
@ApiModel(value = "Rubbish对象", description = "海滩垃圾")
public class RubbishBeach implements Serializable {

    @Id
    @ApiModelProperty("主键ID")
    private Integer id;

    /**
     * 海滩名称
     */
    @ApiModelProperty("海滩名称")
    private String beachName;

    /**
     * 监测日期
     */
    @ApiModelProperty("监测日期")
    private Date monitorDate;

    /**
     * 海滩中心经度
     */
    @ApiModelProperty("海滩中心经度")
    private BigDecimal beachCenterLon;

    /**
     * 海滩中心纬度
     */
    @ApiModelProperty("海滩中心纬度")
    private BigDecimal beachCenterLat;

    /**
     * 数量密度
     */
    @ApiModelProperty("数量密度")
    private BigDecimal numberDensity;

    /**
     * 重量密度
     */
    @ApiModelProperty("重量密度")
    private BigDecimal weightDensity;

    /**
     * 批次
     */
    @ApiModelProperty("批次")
    private String batchFrequency;
}

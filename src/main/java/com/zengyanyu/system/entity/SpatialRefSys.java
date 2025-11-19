package com.zengyanyu.system.entity;

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
 * @since 2025-11-19
 */
@Getter
@Setter
@Entity
@TableName("spatial_ref_sys")
@ApiModel(value = "SpatialRefSys对象", description = "")
public class SpatialRefSys implements Serializable {

    @Id
    private Integer srid;

    @Id
    private String authName;

    private Integer authSrid;

    private String srtext;

    private String proj4text;


}

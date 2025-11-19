package com.zengyanyu.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author zengyanyu
 * @since 2025-11-19
 */
@Getter
@Setter
@Entity
@TableName("spatial_ref_sys")
@ApiModel(value = "SpatialRefSys对象", description = "")
public class SpatialRefSys extends BaseEntity {

    @Id
    private Integer srid;

    @Id
    private String authName;

    private Integer authSrid;

    private String srtext;

    private String proj4text;

}

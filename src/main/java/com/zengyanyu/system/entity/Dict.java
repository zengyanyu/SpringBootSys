package com.zengyanyu.system.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 数据字典
 * </p>
 *
 * @author zengyanyu
 * @since 2025-07-18
 */
@Getter
@Setter
@ApiModel(value = "Dict对象", description = "数据字典")
public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty("字典编码")
    private String code;

    private String name;


}

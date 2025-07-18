package com.zengyanyu.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 数据字典项
 * </p>
 *
 * @author zengyanyu
 * @since 2025-07-18
 */
@Getter
@Setter
@TableName("dict_item")
@ApiModel(value = "DictItem对象", description = "数据字典项")
public class DictItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String dictId;

    @ApiModelProperty("字典项编码")
    private String code;

    @ApiModelProperty("字典项名称")
    private String name;


}

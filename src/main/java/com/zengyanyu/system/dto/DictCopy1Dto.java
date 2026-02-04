package com.zengyanyu.system.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 字典Copy对象 DTO 对象
 *
 * @author zengyanyu
 */
@Data
@Accessors(chain = true)
@ApiModel("DictCopy1Dto对象")
public class DictCopy1Dto implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
             * 字典编码
             */
            @ApiModelProperty("字典编码")
            private String code;

            /**
             * 字典名称
             */
            @ApiModelProperty("字典名称")
            private String name;

}

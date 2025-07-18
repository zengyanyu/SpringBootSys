package com.zengyanyu.system.dto;

import com.zengyanyu.system.util.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("调试日志Dto对象")
public class CallLogDto implements Serializable {

    @ApiModelProperty("时间")
    private LocalDateTime time = DateUtils.getLocalDateTime(System.currentTimeMillis() / 1000);

    @ApiModelProperty("上下行")
    private String upDownLine;

    @ApiModelProperty("日志类型")
    private String logType;

    @ApiModelProperty("方法")
    private String method;

    @ApiModelProperty("clientToken")
    private String clientToken;

    @ApiModelProperty("actionId")
    private String actionId;

    @ApiModelProperty("参数")
    private String params;

    @ApiModelProperty("响应数据")
    private String responseData;

    @ApiModelProperty("编码")
    private String code;

}

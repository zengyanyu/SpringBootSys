package com.zengyanyu.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel("短信发送状态Dto对象")
public class SendStatusDto implements Serializable {

    @ApiModelProperty("序列号")
    private String serialNo;

    @ApiModelProperty("手机号码")
    private String phoneNumber;

    @ApiModelProperty("计费条数")
    private Long fee;

    @ApiModelProperty("用户Session内容")
    private String sessionContext;

    @ApiModelProperty("验证码")
    private String code;

    @ApiModelProperty("短信请求错误码描述")
    private String message;

    @ApiModelProperty("国家码或地区码，例如CN,US等，对于未识别出国家码或者地区码，默认返回DEF,具体支持列表请参考国际/港澳台计费总览")
    private String isoCode;
}

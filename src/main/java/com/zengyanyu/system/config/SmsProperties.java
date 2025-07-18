package com.zengyanyu.system.config;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ApiModel("短信属性配置")
public class SmsProperties {

    @Value("${tencent.sms.secretId}")
    private String secretId;

    @Value("${tencent.sms.secretKey}")
    private String secretKey;

    @Value("${tencent.sms.sdkAppId}")
    private String sdkAppId;

    @Value("${tencent.sms.sign}")
    private String sign;

    @Value("${tencent.sms.templateId}")
    private String templateId;
}

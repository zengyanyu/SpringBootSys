package com.zengyanyu.system.config;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ApiModel("腾讯云配置")
public class TencentCloudConfig {

    @Value("${tencent.SecretId}")
    private String secretId;

    @Value("${tencent.SecretKey}")
    private String secretKey;
}

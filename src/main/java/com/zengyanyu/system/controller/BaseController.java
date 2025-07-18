package com.zengyanyu.system.controller;

import com.zengyanyu.system.config.TencentCloudConfig;
import com.zengyanyu.system.service.IUserInfoService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.iotexplorer.v20190423.IotexplorerClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Resource
    protected HttpServletRequest request;
    @Resource
    protected HttpServletResponse response;
    @Resource
    protected TencentCloudConfig tencentCloudConfig;
    @Resource
    protected IUserInfoService userInfoService;

    /**
     * 获取IotexplorerClient
     *
     * @return 返回 物联网客户端对象
     */
    protected IotexplorerClient getIotexplorerClient() {
        Credential cred = new Credential(tencentCloudConfig.getSecretId(), tencentCloudConfig.getSecretKey());
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("iotexplorer.tencentcloudapi.com");
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        return new IotexplorerClient(cred, "ap-guangzhou", clientProfile);
    }

}

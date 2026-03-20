/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.dto.UserInfoDto;
import com.zengyanyu.system.entity.UserInfo;

/**
 * 用户信息 服务类
 *
 * @author zengyanyu
 * @since 2024-11-18
 */
public interface IUserInfoService extends IService<UserInfo> {

    /**
     * @param userInfoDto
     * @return
     */
    ResponseData login(UserInfoDto userInfoDto);

    /**
     * @param token
     * @return
     */
    ResponseData userInfo(String token);

    /**
     * @param token
     * @return
     */
    ResponseData logout(String token);

    /**
     * @param userInfo
     * @return
     */
    ResponseData saveOrUpdateUserInfo(UserInfoDto userInfo);

    /**
     * 根据token获取用户信息
     *
     * @param token
     * @return
     */
    ResponseData<UserInfo> getUserInfoByToken(String token);

}

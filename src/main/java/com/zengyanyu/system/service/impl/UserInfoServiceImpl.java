/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.dto.UserInfoDto;
import com.zengyanyu.system.entity.UserInfo;
import com.zengyanyu.system.mapper.UserInfoMapper;
import com.zengyanyu.system.service.IUserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户信息 服务实现类
 *
 * @author zengyanyu
 * @since 2024-11-18
 */
@Service
@Transactional
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    /**
     * 登录
     *
     * @param userInfoDto
     * @return
     */
    @Override
    public ResponseData login(UserInfoDto userInfoDto) {
        return null;
    }

    /**
     * 退出
     *
     * @param token
     * @return
     */
    @Override
    public ResponseData logout(String token) {
        return new ResponseData("退出系统成功");
    }

    /**
     * 保存或者修改用户信息
     *
     * @param userInfo 用户信息对象
     * @return
     */
    @Override
    public ResponseData saveOrUpdateUserInfo(UserInfoDto userInfo) {

        return new ResponseData("保存或更新成功");
    }

    /**
     * 根据token获取用户信息
     *
     * @param token
     * @return
     */
    @Override
    public ResponseData<UserInfo> getUserInfoByToken(String token) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("token", token);
        UserInfo userInfo = getOne(wrapper);
        return new ResponseData("根据token获取用户信息成功", userInfo);
    }

}

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
import com.zengyanyu.system.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
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
     * @param dto
     * @return
     */
    @Override
    public ResponseData login(UserInfoDto dto) {
        if (StringUtils.isNotEmpty(dto.getUsername()) && StringUtils.isNotEmpty(dto.getPassword())) {
            // 模拟账号密码----admin/123456
            if ("admin".equals(dto.getUsername()) && "123456".equals(dto.getPassword())) {
                // 查询用户信息
                UserInfo userInfo = getUserInfo(dto.getUsername(), dto.getPassword());
                String token = userInfo.getToken();
                // TOKEN 不为空
                if (StringUtils.isNotEmpty(token)) {
                    // 已过期,重新生成
                    try {
                        if (JwtUtil.isTokenExpired(token)) {
                            token = JwtUtil.generateToken(dto.getUsername());
                            userInfo.setToken(token);
                            saveOrUpdate(userInfo);
                        } else {
                            // 没有过期,设置token
                            dto.setToken(token);
                        }
                    } catch (Exception e) {
                        // 已过期,重新生成
                        token = JwtUtil.generateToken(dto.getUsername());
                        userInfo.setToken(token);
                        saveOrUpdate(userInfo);
                    }
                }
                return new ResponseData("系统登录成功", dto);
            } else {
                return new ResponseData(ResponseData.ERROR_CODE, "账号或者密码不正确!");
            }
        } else {
            return new ResponseData(ResponseData.ERROR_CODE, "账号或者密码不能为空!");
        }
    }

    /**
     * 根据用户名和密码获取用户信息
     *
     * @param username
     * @param password
     * @return
     */
    private UserInfo getUserInfo(String username, String password) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        wrapper.eq("password", password);
        return getOne(wrapper);

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

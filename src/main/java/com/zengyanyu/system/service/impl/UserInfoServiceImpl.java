package com.zengyanyu.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.dto.UserInfoDto;
import com.zengyanyu.system.entity.UserInfo;
import com.zengyanyu.system.mapper.UserInfoMapper;
import com.zengyanyu.system.service.IUserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * 用户信息 服务实现类
 *
 * @author zengyanyu
 * @since 2024-11-18
 */
@Service
@Transactional
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService, UserDetailsService {

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
     * 获取登录成功的用户信息
     *
     * @param token
     * @return
     */
    @Override
    public ResponseData userInfo(String token) {
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
     * 是否锁定修改状态
     *
     * @param userInfo 用户信息
     * @return
     */
    @Override
    public ResponseData isLockUpdate(UserInfoDto userInfo) {
        return new ResponseData("状态修改成功");
    }

    /**
     * 重置密码
     *
     * @param userInfo
     * @return
     */
    @Override
    public ResponseData resetPassword(UserInfoDto userInfo) {
        if (StringUtils.isNotEmpty(userInfo.getPassword())) {
            if (userInfo.getPassword().trim().length() < 6) {
                return new ResponseData(ResponseData.ERROR_CODE, "密码长度不能小于6位");
            }
        }
        // 去除空字符串
        userInfo.setPassword(userInfo.getPassword().trim());
        this.saveOrUpdate(userInfo);
        return new ResponseData("重置密码成功");
    }

    /**
     * 根据token获取用户信息
     *
     * @param token
     * @return
     */
    @Override
    public ResponseData<UserInfo> getUserInfoByToken(String token) {
        UserInfo userInfo = this.baseMapper.userInfo(token);
        return new ResponseData("根据token获取用户信息成功", userInfo);
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名称，手机号码
     * @return
     */
    @Override
    public ResponseData<UserInfo> getUserInfoByUsername(String username) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        UserInfo userInfo = this.getOne(wrapper);
        if (null != userInfo) {
            return new ResponseData("根据用户名查询用户信息成功", userInfo);
        }
        return new ResponseData(ResponseData.SUCCEED, "暂未查询到数据", null);
    }

    /**
     * 判断是否是管理员/管理员用户
     *
     * @return
     */
    @Override
    public Boolean isAdminUser() {
        return false;
    }

    /**
     * 根据用户名称加载用户
     *
     * @param username 用户名称
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = this.getUserInfoByUsername(username).getData();
        if (null != userInfo) {
            return new User(username, userInfo.getToken(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

}

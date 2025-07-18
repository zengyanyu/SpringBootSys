package com.zengyanyu.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.dto.UserInfoDto;
import com.zengyanyu.system.entity.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
     * 是否锁定修改状态
     *
     * @param userInfo
     * @return
     */
    ResponseData isLockUpdate(UserInfoDto userInfo);

    /**
     * 重置密码
     *
     * @param userInfo
     * @return
     */
    ResponseData resetPassword(UserInfoDto userInfo);

    /**
     * 根据token获取用户信息
     *
     * @param token
     * @return
     */
    ResponseData<UserInfo> getUserInfoByToken(String token);

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名称，手机号码
     * @return
     */
    ResponseData<UserInfo> getUserInfoByUsername(String username);

    /**
     * 判断是否是管理员/管理员用户
     *
     * @return
     */
    Boolean isAdminUser();

    /**
     * 根据用户名称加载用户
     *
     * @param username 用户名称
     * @return
     */
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}

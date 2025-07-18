package com.zengyanyu.system.service;

import com.zengyanyu.system.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;

/**
 * 用户关联的角色 服务类
 *
 * @author zengyanyu
 * @since 2025-07-18
 */
public interface IUserRoleService extends IService<UserRole> {

    /**
     * 保存或更新用户关联的角色
     *
     * @param userRole 用户关联的角色
     * @return
     */
    ResponseData saveOrUpdateUserRole(UserRole userRole);

}

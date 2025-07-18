package com.zengyanyu.system.service;

import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 角色 服务类
 *
 * @author zengyanyu
 * @since 2024-11-19
 */
public interface IRoleService extends IService<Role> {

    /**
     * 保存或更新角色
     *
     * @param role
     * @return
     */
    ResponseData saveOrUpdateRole(Role role);
}

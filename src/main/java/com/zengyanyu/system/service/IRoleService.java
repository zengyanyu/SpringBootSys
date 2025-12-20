package com.zengyanyu.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.entity.Role;

import java.io.InputStream;
import java.util.List;

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

    /**
     * 批量保存
     *
     * @param roleList
     */
    void batchSave(List<Role> roleList);

    /**
     * @param roleCode
     * @return
     */
    Boolean selectDataByCondition(String roleCode);

    /**
     * 导入Excel文件
     *
     * @param inputStream 文件输入流
     */
    void importExcel(InputStream inputStream);
}

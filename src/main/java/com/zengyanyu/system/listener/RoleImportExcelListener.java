/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.listener;

import com.zengyanyu.system.dto.RoleImportExcelDto;
import com.zengyanyu.system.entity.Role;
import com.zengyanyu.system.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色导入Excel监听器
 *
 * @author zengyanyu
 */
@Slf4j
public class RoleImportExcelListener extends BaseImportExcelListener<RoleImportExcelDto> {

    private final IRoleService roleService;

    public RoleImportExcelListener(IRoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    protected boolean isDataExist(RoleImportExcelDto data) {
        return roleService.selectDataByCondition(data.getRoleCode());
    }

    @Override
    protected void saveData() {
        log.info("执行批量处理,数据量: {}", cachedDataList.size());

        List<Role> roleList = new ArrayList<>();
        for (RoleImportExcelDto dto : cachedDataList) {
            Role role = new Role();
            BeanUtils.copyProperties(dto, role);
            roleList.add(role);
        }

        roleService.batchSave(roleList);
    }
}

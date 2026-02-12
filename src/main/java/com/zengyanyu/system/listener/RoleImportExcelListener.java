package com.zengyanyu.system.listener;

import com.zengyanyu.system.dto.RoleImportExcelDto;
import com.zengyanyu.system.entity.Role;
import com.zengyanyu.system.service.IRoleService;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色导入Excel监听器
 *
 * @author zengyanyu
 */
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
        logger.info("执行批量处理,数据量: {}", cachedDataList.size());

        List<Role> roleList = new ArrayList<>();
        for (RoleImportExcelDto dto : cachedDataList) {
            Role role = new Role();
            BeanUtils.copyProperties(dto, role);
            roleList.add(role);
        }

        roleService.batchSave(roleList);
    }
}

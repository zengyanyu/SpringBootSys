package com.zengyanyu.system.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.dto.RoleImportExcelDto;
import com.zengyanyu.system.entity.Role;
import com.zengyanyu.system.listener.RoleImportExcelListener;
import com.zengyanyu.system.mapper.RoleMapper;
import com.zengyanyu.system.service.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色 服务实现类
 *
 * @author zengyanyu
 * @since 2024-11-19
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    /**
     * 保存或更新角色
     *
     * @param role
     * @return
     */
    @Override
    public ResponseData saveOrUpdateRole(Role role) {
        List<Role> list = this.list();
        List<String> items = new ArrayList<>();
        for (Role item : list) {
            // 编辑的时候，不做编码重复校验逻辑
            if (!(role.getId() != null && role.getId().equals(item.getId()))) {
                items.add(item.getRoleCode());
            }
        }
        if (items.contains(role.getRoleCode())) {
            return new ResponseData(ResponseData.ERROR_CODE, "角色编码重复，请重新输入角色编码！重复角色编码为 " + role.getRoleCode());
        }
        this.saveOrUpdate(role);
        return new ResponseData("保存或更新角色成功");
    }

    /**
     * 批量保存
     *
     * @param roleList
     */
    @Override
    public void batchSave(List<Role> roleList) {
        // 调用service接口中的方法(框架封装)
        this.saveBatch(roleList);
    }

    @Override
    public Boolean selectDataByCondition(String roleCode) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq("role_code", roleCode);
        return null != this.getOne(wrapper);
    }

    /**
     * 导入Excel文件
     *
     * @param inputStream 文件输入流
     */
    @Override
    public void importExcel(InputStream inputStream) {
        EasyExcel.read(inputStream, RoleImportExcelDto.class,
                new RoleImportExcelListener(this))
                .charset(StandardCharsets.UTF_8).sheet().doRead();
    }

}

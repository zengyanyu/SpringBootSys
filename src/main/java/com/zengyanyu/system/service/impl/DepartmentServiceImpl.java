package com.zengyanyu.system.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.dto.DepartmentImportExcelDto;
import com.zengyanyu.system.entity.Department;
import com.zengyanyu.system.listener.DepartmentImportExcelListener;
import com.zengyanyu.system.mapper.DepartmentMapper;
import com.zengyanyu.system.service.IDepartmentService;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 部门管理 服务实现类
 *
 * @author zengyanyu
 * @since 2025-07-18
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    /**
     * 保存或更新部门管理
     *
     * @param department 部门管理
     * @return
     */
    @Override
    public ResponseData saveOrUpdateDepartment(Department department) {
        this.saveOrUpdate(department);
        return new ResponseData("保存或更新成功");
    }

    /**
     * 批量保存
     *
     * @param departmentList
     */
    @Override
    public void batchSave(List<Department> departmentList) {
        this.saveBatch(departmentList);
    }

    /**
     * 导入Excel文件
     *
     * @param inputStream 文件输入流
     */
    @Override
    public void importExcel(InputStream inputStream) {
        EasyExcel.read(inputStream, DepartmentImportExcelDto.class,
                new DepartmentImportExcelListener(this))
                .charset(StandardCharsets.UTF_8).sheet().doRead();
    }
}

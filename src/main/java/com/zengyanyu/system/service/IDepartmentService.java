package com.zengyanyu.system.service;

import com.zengyanyu.system.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;

import java.util.List;

/**
 * 部门管理 服务类
 *
 * @author zengyanyu
 * @since 2025-07-18
 */
public interface IDepartmentService extends IService<Department>, IImportExcelService {

    /**
     * 保存或更新部门管理
     *
     * @param department 部门管理
     * @return
     */
    ResponseData saveOrUpdateDepartment(Department department);

    /**
     * 批量保存
     * @param departmentList
     */
    void batchSave(List<Department> departmentList);
}

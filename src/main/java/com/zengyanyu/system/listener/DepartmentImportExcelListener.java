package com.zengyanyu.system.listener;

import com.zengyanyu.system.dto.DepartmentImportExcelDto;
import com.zengyanyu.system.entity.Department;
import com.zengyanyu.system.service.IDepartmentService;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门导入Excel监听器
 *
 * @author zengyanyu
 */
public class DepartmentImportExcelListener extends BaseImportExcelListener<DepartmentImportExcelDto> {

    private final IDepartmentService departmentService;

    public DepartmentImportExcelListener(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    protected boolean isDataExist(DepartmentImportExcelDto data) {
        // 这里原本返回false，保留原逻辑
        return false;
    }

    @Override
    protected void saveData() {
        logger.info("执行批量处理,数据量: {}", cachedDataList.size());

        List<Department> departmentList = new ArrayList<>();
        for (DepartmentImportExcelDto dto : cachedDataList) {
            Department department = new Department();
            BeanUtils.copyProperties(dto, department);
            departmentList.add(department);
        }

        departmentService.batchSave(departmentList);
    }
}

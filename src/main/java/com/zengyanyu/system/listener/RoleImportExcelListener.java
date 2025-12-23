package com.zengyanyu.system.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zengyanyu.system.dto.RoleImportExcelDto;
import com.zengyanyu.system.entity.Role;
import com.zengyanyu.system.service.IRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zengyanyu
 */
public class RoleImportExcelListener extends AnalysisEventListener<RoleImportExcelDto> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleImportExcelListener.class);

    /**
     * 批量处理阈值
     */
    private static final int BATCH_SIZE = 100;

    /**
     * 执行批量处理,数据量
     */
    private List<RoleImportExcelDto> roleDotList = new ArrayList<>();

    @Resource
    private IRoleService roleService;

    public RoleImportExcelListener(IRoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 逐行读取Excel数据(每读一行执行一次)
     *
     * @param data
     * @param context
     */
    @Override
    public void invoke(RoleImportExcelDto data, AnalysisContext context) {
        // 数据校验
        if (isDataExist(data)) {
            LOGGER.info("读取到Excel数据: {},数据在数据库中已存在!", data);
            return;
        }
        LOGGER.info("读取到Excel数据: {}", data);

        // add
        roleDotList.add(data);

        // 达到批量阈值,执行批量处理
        if (roleDotList.size() >= BATCH_SIZE) {
            batchSave();
            // 清空列表,释放内存
            roleDotList.clear();
        }
    }

    /**
     * Excel读取完成后执行(最后一批数据处理)
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 处理剩余不足批量阈值的数据
        if (!roleDotList.isEmpty()) {
            batchSave();
        }
        LOGGER.info("Excel数据读取成功,共处理{}条数据", context.readRowHolder().getRowIndex());
    }

    /**
     * 批量处理数据
     */
    private void batchSave() {
        LOGGER.info("执行批量处理,数据量: {}", roleDotList.size());
        // 调用业务方法
        List<Role> dictList = new ArrayList<>();
        for (RoleImportExcelDto dictDto : roleDotList) {
            // 创建对象,设置属性
            Role role = new Role();
            BeanUtils.copyProperties(dictDto, role);
            // add
            dictList.add(role);
        }
        // 批量保存
        roleService.batchSave(dictList);
    }

    /**
     * 数据是否存在
     *
     * @param data
     * @return
     */
    private Boolean isDataExist(RoleImportExcelDto data) {
        return roleService.selectDataByCondition(data.getRoleCode());
    }
}

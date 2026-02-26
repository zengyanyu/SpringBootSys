package com.zengyanyu.system.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Excel导入监听器基类
 * 使用模板方法模式，将通用的批量处理逻辑抽取到父类
 *
 * @param <DTO> Excel行数据对应的DTO类型
 */
public abstract class BaseImportExcelListener<DTO> extends AnalysisEventListener<DTO> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 批量处理阈值
     */
    private static final int BATCH_SIZE = 100;

    /**
     * 缓存的数据列表
     */
    protected List<DTO> cachedDataList = new ArrayList<>();

    /**
     * 逐行读取Excel数据(每读一行执行一次)
     */
    @Override
    public void invoke(DTO data, AnalysisContext context) {
        // 数据校验：如果数据已存在，跳过
        if (isDataExist(data)) {
            logger.info("读取到Excel数据: {},数据在数据库中已存在!", data);
            return;
        }
        logger.info("读取到Excel数据: {}", data);

        // 加入缓存
        cachedDataList.add(data);

        // 达到批量阈值,执行批量处理
        if (cachedDataList.size() >= BATCH_SIZE) {
            saveData();
            // 清空列表,释放内存
            cachedDataList.clear();
        }
    }

    /**
     * Excel读取完成后执行(最后一批数据处理)
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 处理剩余不足批量阈值的数据
        if (!cachedDataList.isEmpty()) {
            saveData();
        }
        logger.info("Excel数据读取成功,共处理{}条数据", context.readRowHolder().getRowIndex());
    }

    /**
     * 判断数据是否已存在（由子类实现具体逻辑）
     *
     * @param data 读取到的行数据
     * @return true:存在, false:不存在
     */
    protected abstract boolean isDataExist(DTO data);

    /**
     * 执行批量保存操作（由子类实现具体逻辑）
     */
    protected abstract void saveData();
}

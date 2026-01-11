package com.zengyanyu.system.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.zengyanyu.system.dto.DictImportExcelDto;
import com.zengyanyu.system.entity.Dict;
import com.zengyanyu.system.service.IDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zengyanyu
 */
public class DictImportExcelListener extends AnalysisEventListener<DictImportExcelDto> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DictImportExcelListener.class);

    /**
     * 批量处理阈值
     */
    private static final int BATCH_SIZE = 100;

    /**
     * 执行批量处理,数据量
     */
    private List<DictImportExcelDto> dictDtoList = new ArrayList<>();

    @Resource
    private IDictService dictService;

    public DictImportExcelListener(IDictService dictService) {
        this.dictService = dictService;
    }

    /**
     * 逐行读取Excel数据(每读一行执行一次)
     *
     * @param data
     * @param context
     */
    @Override
    public void invoke(DictImportExcelDto data, AnalysisContext context) {
        // 数据校验
        if (isDataExist(data)) {
            LOGGER.info("读取到Excel数据: {},数据在数据库中已存在!", data);
            return;
        }
        LOGGER.info("读取到Excel数据: {}", data);

        // add
        dictDtoList.add(data);

        // 达到批量阈值,执行批量处理
        if (dictDtoList.size() >= BATCH_SIZE) {
            batchSave();
            // 清空列表,释放内存
            dictDtoList.clear();
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
        if (!dictDtoList.isEmpty()) {
            batchSave();
        }
        LOGGER.info("Excel数据读取成功,共处理{}条数据", context.readRowHolder().getRowIndex());
    }

    /**
     * 批量处理数据
     */
    private void batchSave() {
        LOGGER.info("执行批量处理,数据量: {}", dictDtoList.size());
        // 调用业务方法
        List<Dict> dictList = new ArrayList<>();
        for (DictImportExcelDto dictDto : dictDtoList) {
            // 创建对象,设置属性
            Dict dict = new Dict();
            BeanUtils.copyProperties(dictDto, dict);
            // add
            dictList.add(dict);
        }
        // 批量保存
        dictService.batchSave(dictList);
    }

    /**
     * 数据是否存在
     *
     * @param data
     * @return
     */
    private Boolean isDataExist(DictImportExcelDto data) {
        return dictService.selectDataByCondition(data.getCode());
    }

    /**
     * 第一步执行：读取表头
     * 只执行一次（除非有多行表头）
     *
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
        System.out.println(">>> [invokeHead] 正在读取表头...");

        String[] titleNameList = new String[]{"字典编码标题", "字典名称标题"};

        // 遍历表头
        for (Map.Entry<Integer, ReadCellData<?>> entry : headMap.entrySet()) {
            Integer colIndex = entry.getKey();
            // 获取表头文字
            String titleName = titleNameList[colIndex];
            String headName = entry.getValue().getStringValue();
            if (!titleName.equals(headName)) {
                throw new RuntimeException("请下载合适的Excel文件模板进行上传;  标题名称为[ " + headName + " ]不匹配");
            }
        }
        System.out.println(">>> [invokeHead] 表头读取结束，准备读取数据。\n");
    }
}

package com.zengyanyu.system.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zengyanyu.system.dto.DictImportExcelDto;
import com.zengyanyu.system.entity.Dict;
import com.zengyanyu.system.service.IDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
//        LOGGER.info("读取到Excel数据: {}", data);
        // 数据校验

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
}

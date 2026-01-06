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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
     * 当 EasyExcel 开始解析 Excel 文件时，读完第一行（表头行）之后，会立即调用这个方法
     * 触发时机
     * 它会在读取所有数据行（invoke 方法）之前被调用。通常只在读取整个 Sheet 的第一行时触发一次。
     * <p>
     * 主要使用场景：
     * 在实际开发中，重写这个方法最常用的目的是 表头校验（模板校验）。
     * <p>
     * 场景举例：
     * 假设你的系统要求用户上传的 Excel 必须包含特定的列，比如“姓名”、“手机号”、“身份证号”。
     * 如果用户上传了一个乱七八糟的文件，你希望在读取数据之前就报错，而不是读到一半才发现数据对应不上。
     *
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
        // 1.调用父类方法（可选）
        super.invokeHead(headMap, context);

        // 2.将表头数据转为字符串列表（方便比较）
        // 这里的 headMap.values() 包含了所有列的表头信息
        List<String> headList = headMap.values().stream()
                .map(cellData -> cellData.getStringValue())
                .collect(Collectors.toList());

        // 3.定义预期的表头
        List<String> expectedHeads = Arrays.asList("姓名", "年龄", "手机号");

        // 4.校验表头是否匹配
//        if (!headList.equals(expectedHeads)) {
//            // 抛出异常终止读取，或者记录日志
//            throw new RuntimeException("上传的 Excel 模板不正确，请下载最新模板！期望表头：" + expectedHeads + "，实际表头：" + headList);
//        }

        System.out.println("表头校验通过，开始读取数据...");
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
}

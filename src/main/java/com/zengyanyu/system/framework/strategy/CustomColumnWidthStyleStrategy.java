package com.zengyanyu.system.framework.strategy;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.style.column.AbstractColumnWidthStyleStrategy;
import org.apache.poi.ss.usermodel.Cell;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义列宽样式策略
 *
 * @author zengyanyu
 */
public class CustomColumnWidthStyleStrategy extends AbstractColumnWidthStyleStrategy {

    // 缓存已设置过宽度的列，避免重复设置，提高性能
    private Map<Integer, Map<Integer, Integer>> cache = new HashMap<>();

    /**
     * 设置列宽
     *
     * @param writeSheetHolder
     * @param cellDataList
     * @param cell             单元格
     * @param head             表头信息
     * @param relativeRowIndex 当前行
     * @param isHead           是否是表头
     */
    @Override
    protected void setColumnWidth(WriteSheetHolder writeSheetHolder, List<WriteCellData<?>> cellDataList,
                                  Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        // 需要设置宽度
        if (isHead || !cache.containsKey(writeSheetHolder.getSheetNo())) {
            Map<Integer, Integer> maxColumnWidthMap = cache.computeIfAbsent(writeSheetHolder.getSheetNo(), k -> new HashMap<>());

            Integer columnWidth = this.dataLength();

            // 如果计算出的长度比之前的长度大，则更新（这里主要为了逻辑完整，固定宽度其实可以直接返回固定值）
            Integer maxColumnWidth = maxColumnWidthMap.get(cell.getColumnIndex());
            if (maxColumnWidth == null || columnWidth > maxColumnWidth) {
                maxColumnWidthMap.put(cell.getColumnIndex(), columnWidth);
                // 单位是 1/256 个字符
                writeSheetHolder.getSheet().setColumnWidth(cell.getColumnIndex(), columnWidth * 256);
            }
        }
    }

    /**
     * 固定的宽度值
     *
     * @return 16
     */
    private Integer dataLength() {
        // 设置默认列宽（字符数）
        return 16;
    }
}
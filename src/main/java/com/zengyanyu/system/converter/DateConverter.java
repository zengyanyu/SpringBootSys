/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换器
 *
 * @author zengyanyu
 */
public class DateConverter implements Converter<Date> {

    private static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 代表：我这个转换器专门处理 Java 里的 Date 类型字段
     *
     * @return
     */
    @Override
    public Class<Date> supportJavaTypeKey() {
        return Date.class;
    }

    /**
     * 代表：Excel 里存的是字符串（比如 "2025-01-01"）
     *
     * @return
     */
    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 写入 Excel 时调用：Date → String
     *
     * @param value
     * @param excelContentProperty
     * @param globalConfiguration
     * @return
     * @throws Exception
     */
    @Override
    public WriteCellData<?> convertToExcelData(Date value, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_YYYY_MM_DD);
        String dateValue = sdf.format(value);
        return new WriteCellData(dateValue);
    }

}

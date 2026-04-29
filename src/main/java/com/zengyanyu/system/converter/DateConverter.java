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
import com.alibaba.excel.metadata.property.DateTimeFormatProperty;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.alibaba.excel.util.StringUtils;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 动态日期转换器（支持自定义格式）<br>
 * 这个转换器可以配合 com.alibaba.excel.annotation.format.DateTimeFormat.@DateTimeFormat("yyyy") 阿里巴巴的包配合使用，可以设置自定义的格式,默认为yyyy-MM-dd<br>
 * 注意点：不使用 org.springframework.format.annotation.DateTimeFormat.@DateTimeFormat(pattern = "yyyy") 这个spring框架中的注解，对这个转换器是无效的
 *
 * @author zengyanyu
 */
public class DateConverter implements Converter<Date> {

    /**
     * 默认格式
     */
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd";

    /**
     * 代表：我这个转换器专门处理 Java 里的 Date 类型字段
     *
     * @return Date类
     */
    @Override
    public Class<?> supportJavaTypeKey() {
        return Date.class;
    }

    /**
     * 代表：Excel 里存的是字符串（比如 "2025-01-01"）
     *
     * @return 字符串
     */
    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 写入 Excel 时调用：Date → String
     *
     * @param value               数据值
     * @param contentProperty     内容属性
     * @param globalConfiguration 全局配置
     * @return Excel单元格数据
     */
    @Override
    public WriteCellData<?> convertToExcelData(Date value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        // 1. 获取注解上的格式
        String pattern = DEFAULT_PATTERN;

        if (contentProperty != null) {
            DateTimeFormatProperty prop = contentProperty.getDateTimeFormatProperty();
            if (prop != null && StringUtils.isNotBlank(prop.getFormat())) {
                pattern = prop.getFormat();
            }
        }

        // 线程安全的时间格式化
        String dateValue = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault()).format(value.toInstant());
        return new WriteCellData<>(dateValue);
    }

}

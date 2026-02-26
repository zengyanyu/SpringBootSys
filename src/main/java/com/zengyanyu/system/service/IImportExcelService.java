package com.zengyanyu.system.service;

import java.io.InputStream;

/**
 * @author zengyanyu
 */
@FunctionalInterface
public interface IImportExcelService {

    /**
     * 导入 Excel 文件
     *
     * @param inputStream 文件输入流
     */
    void importExcel(InputStream inputStream);
}

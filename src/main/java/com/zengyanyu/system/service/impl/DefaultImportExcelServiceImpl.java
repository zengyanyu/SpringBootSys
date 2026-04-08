package com.zengyanyu.system.service.impl;

import com.zengyanyu.system.service.IImportExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * 默认Excel导入服务（兜底）
 * @author zengyanyu
 */
@Slf4j
@Service("defaultImportExcelService")
public class DefaultImportExcelServiceImpl implements IImportExcelService {

    @Override
    public void importExcel(InputStream inputStream) {
        log.info("【默认服务】开始导入Excel文件...");
        // 通用导入逻辑
        log.info("【默认服务】Excel导入完成");
    }
}

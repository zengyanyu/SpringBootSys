/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.task;

import com.zengyanyu.system.config.ExcelImportProperties;
import com.zengyanyu.system.config.ImportExcelServiceFactory;
import com.zengyanyu.system.service.IImportExcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.Executor;

/**
 * Excel定时导入任务：按文件夹匹配对应服务
 *
 * @author zengyanyu
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ExcelImportTask {

    private final ExcelImportProperties excelImportProperties;
    private final ImportExcelServiceFactory importExcelServiceFactory;
    /**
     * 注入线程池
     */
    private final Executor fileExecutor;

    /**
     * 定时任务：从yml读取cron表达式
     */
    @Scheduled(cron = "#{excelImportProperties.cron}")
    public void startImportTask() {
        String rootScanPath = excelImportProperties.getScanPath();
        File rootDir = new File(rootScanPath);

        // 根目录校验
        if (!rootDir.exists() || !rootDir.isDirectory()) {
            log.error("根扫描目录不存在或不是文件夹：{}", rootScanPath);
            return;
        }

        // 是否启动的开关
        if (excelImportProperties.getIsStarted()) {
            log.info("==================== 开始定时导入Excel（按文件夹匹配服务） ====================");
            // 线程池异步执行根目录扫描
            fileExecutor.execute(() -> scanDirAndImport(rootDir));
        }
    }

    /**
     * 递归扫描目录，按文件夹匹配服务导入Excel
     */
    private void scanDirAndImport(File dir) {
        if (dir == null || !dir.exists()) {
            return;
        }

        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            log.info("目录为空：{}", dir.getAbsolutePath());
            return;
        }

        // 获取当前文件夹名称（用于匹配服务）
        String currentFolderName = dir.getName();
        log.info("扫描目录：{}，文件夹名称：{}", dir.getAbsolutePath(), currentFolderName);

        for (File file : files) {
            // 1. 如果是子目录 → 递归扫描
            if (file.isDirectory()) {
                scanDirAndImport(file);
                continue;
            }

            // 2. 如果是Excel文件 → 按当前文件夹匹配服务导入
            if (isExcelFile(file)) {
                fileExecutor.execute(() -> importExcelByFolder(file, currentFolderName));
            }
        }
    }

    /**
     * 根据文件夹名称匹配对应服务，导入Excel
     */
    private void importExcelByFolder(File excelFile, String folderName) {
        try (InputStream inputStream = new FileInputStream(excelFile)) {
            log.info("【{}】开始导入Excel：{}", folderName, excelFile.getName());

            // 1. 根据文件夹名称获取对应导入服务
            IImportExcelService importService = importExcelServiceFactory.getServiceByFolderName(folderName);
            if (importService == null) {
                log.error("【{}】无对应导入服务，跳过文件：{}", folderName, excelFile.getName());
                return;
            }

            // 2. 执行导入
            importService.importExcel(inputStream);

            log.info("【{}】导入成功：{}", folderName, excelFile.getName());

        } catch (Exception e) {
            log.error("【{}】导入失败：{}，原因：{}", folderName, excelFile.getName(), e.getMessage(), e);
        }
    }

    /**
     * 判断是否为Excel文件
     */
    private boolean isExcelFile(File file) {
        if (file.isDirectory() || !file.exists()) {
            return false;
        }
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".xls") || fileName.endsWith(".xlsx");
    }
}

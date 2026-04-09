package com.zengyanyu.system.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Excel导入配置：文件夹与对应服务的映射
 *
 * @author zengyanyu
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "excel.import")
public class ExcelImportProperties {
    /**
     * 文件夹名称 -> 对应Service的Bean名称 映射
     * 示例：
     * user-data: userImportExcelService
     * order-data: orderImportExcelService
     */
    private Map<String, String> folderServiceMap;

    /**
     * 默认服务Bean名称（未匹配到文件夹时使用）
     */
    private String defaultService;

    /**
     * 扫描路径
     */
    private String scanPath;

    /**
     * 定时任务表达式
     */
    private String cron;

    /**
     * 设置是否启用开关
     */
    private Boolean isStarted;
}

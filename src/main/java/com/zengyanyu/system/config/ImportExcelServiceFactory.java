package com.zengyanyu.system.config;

import com.zengyanyu.system.service.IImportExcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Excel导入服务工厂：根据文件夹名称获取对应Service
 * @author zengyanyu
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ImportExcelServiceFactory {

    private final ApplicationContext applicationContext;
    private final ExcelImportProperties excelImportProperties;

    /**
     * 根据文件夹名称获取对应的导入服务
     *
     * @param folderName 文件夹名称
     * @return 对应导入服务
     */
    public IImportExcelService getServiceByFolderName(String folderName) {
        // 1. 从配置中获取对应Service的Bean名称
        String serviceBeanName = excelImportProperties.getFolderServiceMap().get(folderName);

        // 2. 未匹配到文件夹，使用默认服务
        if (serviceBeanName == null || serviceBeanName.isEmpty()) {
            String defaultServiceName = excelImportProperties.getDefaultService();
            if (defaultServiceName != null && !defaultServiceName.isEmpty()) {
                log.warn("文件夹【{}】未配置对应服务，使用默认服务【{}】", folderName, defaultServiceName);
                return getServiceByName(defaultServiceName);
            }
            log.error("文件夹【{}】未配置对应服务，且无默认服务，无法导入", folderName);
            return null;
        }

        // 3. 根据Bean名称获取服务实例
        return getServiceByName(serviceBeanName);
    }

    /**
     * 根据Bean名称获取服务实例
     *
     * @param serviceBeanName Service的Bean名称
     * @return 服务实例
     */
    private IImportExcelService getServiceByName(String serviceBeanName) {
        try {
            return applicationContext.getBean(serviceBeanName, IImportExcelService.class);
        } catch (Exception e) {
            log.error("获取导入服务【{}】失败，请检查Bean名称是否正确", serviceBeanName, e);
            return null;
        }
    }
}

package com.zengyanyu.system.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 应用上下文配置类
 *
 * @author zengyanyu
 */
@Component
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        synchronized (ApplicationContextHelper.class) {
            ApplicationContextHelper.applicationContext = applicationContext;
        }
    }

    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext != null ? applicationContext.getBean(requiredType) : null;
    }

    public static Object getBean(String name) {
        return applicationContext != null ? applicationContext.getBean(name) : null;
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return applicationContext != null ? applicationContext.getBeansOfType(clazz) : null;
    }

    /**
     * 手动获取配置值
     *
     * @param key
     * @return
     */
    public static String getConfigValue(String key) {
        return applicationContext.getEnvironment().getProperty(key);
    }

}

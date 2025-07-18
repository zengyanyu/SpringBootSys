package com.zengyanyu.system.util;

import java.lang.reflect.Field;

/**
 * 反射工具类
 */
public class ReflectionUtil {

    private ReflectionUtil() {
    }

    /**
     * 根据对象和字段名称获取字段对应值
     *
     * @param object    对象
     * @param fieldName 字段名称
     * @return
     */
    public static Object getValue(Object object, String fieldName) {
        try {
            // 若使用超类里面的字段，请使用getSuperclass()方法
            Field field = object.getClass().getSuperclass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}

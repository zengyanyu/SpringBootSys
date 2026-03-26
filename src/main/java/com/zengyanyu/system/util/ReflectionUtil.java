/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.util;

import com.zengyanyu.system.entity.Department;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

/**
 * 反射工具类
 *
 * @author zengyanyu
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReflectionUtil {

    /**
     * 根据对象和字段名称获取字段对应值
     *
     * @param object    对象
     * @param fieldName 字段名称
     * @return
     */
    public static Object getValue(Object object, String fieldName) {
        // 空值校验
        if (object == null) {
            throw new IllegalArgumentException("对象不能为空！");
        }

        Class<?> clazz = object.getClass();

        // 循环向上遍历所有父类查找字段
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field.get(object);
            } catch (NoSuchFieldException e) {
                // 当前类找不到，继续找父类
                clazz = clazz.getSuperclass();
            } catch (Exception e) {
                throw new RuntimeException("获取字段值失败：" + fieldName, e);
            }
        }

        // 遍历完所有类都没找到 → 直接抛明确异常
        throw new NoSuchFieldError("在类【" + object.getClass().getName() + "】及其父类中，未找到字段：" + fieldName);
    }

    public static void main(String[] args) {
        Department dept = new Department();
        dept.setDeptName("1111111111");
        dept.setCreateTime(LocalDateTime.now());
        Object deptName = getValue(dept, "deptName");
        System.out.println("deptName = " + deptName);
        Object createTime = getValue(dept, "createTime");
        System.out.println("createTime = " + createTime);
    }

}

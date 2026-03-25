/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.util;

import com.zengyanyu.system.dto.Person;
import com.zengyanyu.system.entity.BaseEntity;
import com.zengyanyu.system.entity.Department;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zengyanyu
 */
public class BeanUtil {

    /**
     * javaBean转换成Map
     *
     * @param obj 要转换的javaBean对象
     * @return
     * @throws Exception
     */
    public static Map<String, Object> bean2map(Object obj) throws Exception {
        Map<String, Object> map = new HashMap<>();
        // 获取javaBean属性描述的包装对象
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass(), Object.class);
        // 获取属性描述器
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            // 获取属性名
            String name = pd.getName();
            // 获取属性值
            Method getMethod = pd.getReadMethod();
            Object value = getMethod.invoke(obj);
            map.put(name, value);
        }
        return map;
    }

    /**
     * map转换成javaBean
     *
     * @param map
     * @param clazz 要转换的javaBean类型
     * @return
     * @throws Exception
     */
    public static <T> T map2bean(Map<String, Object> map, Class<T> clazz) throws Exception {
        // 获取javaBean属性描述的包装对象
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Object.class);
        // 获取属性描述器
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        T obj = clazz.newInstance();
        for (PropertyDescriptor pd : pds) {
            // 获取属性名
            String name = pd.getName();
            // 获取属性值
            Method setMethod = pd.getWriteMethod();
            setMethod.invoke(obj, map.get(name));
        }
        return obj;
    }

    public static void main(String[] args) throws Exception {
//        test1();
//        test2();
        test3();
    }

    private static void test3() throws Exception {
        // 这里传入 子类.class、父类.class（会自动忽略父类中的 class 属性）
        BeanInfo beanInfo = Introspector.getBeanInfo(Department.class, BaseEntity.class.getSuperclass());

        // 获取所有属性描述器
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();

        for (PropertyDescriptor pd : pds) {
            // 属性名：userName → userName
            String fieldName = pd.getName();

            // 属性类型：String、Integer、Date等
            Class<?> fieldType = pd.getPropertyType();

            // ✅ 关键：通过属性名反射拿到真实字段（包括父类字段）
            Field field = getField(Department.class, fieldName);

            System.out.println("========================");
            System.out.println("字段名：" + field.getName());
            System.out.println("字段类型：" + fieldType.getName());
            System.out.println("字段注解数量：" + field.getAnnotations().length);

            // 打印所有注解
            Arrays.stream(field.getAnnotations()).forEach(anno -> {
                System.out.println("注解：" + anno.annotationType().getName());
            });
        }
    }

    /**
     * 递归获取当前类 + 父类字段
     */
    public static Field getField(Class<?> clazz, String fieldName) {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class<?> superClazz = clazz.getSuperclass();
            if (superClazz != null && superClazz != Object.class) {
                return getField(superClazz, fieldName);
            }
        }
        return null;
    }

    private static void test2() throws Exception {
        // 获取javaBean属性描述的包装对象
        BeanInfo beanInfo = Introspector.getBeanInfo(Department.class, BaseEntity.class);
        // 获取属性描述器
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            String name = pd.getName();
            Class<?> propertyType = pd.getPropertyType();
            String str = "private " + propertyType.getSimpleName() + " " + name + ";";
            System.out.println(str);
        }
    }

    private static void test1() throws Exception {
        Person person = new Person("曾衍育", 18, "男");
        Map<String, Object> map = bean2map(person);
        System.out.println(map);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "曾衍育");
        map2.put("age", 18);
        map2.put("grande", "男");
        Person person1 = map2bean(map2, Person.class);
        System.out.println("person1 = " + person1);
    }

}

package com.zengyanyu.system.util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 包扫描工具
 *
 * @author zengyanyu
 */
public class ClassScannerUtil {

    /**
     * 根据包名获取包中的所有类
     *
     * @param packageName 包名
     * @return 类集合
     */
    public static List<Class<?>> getClasses(String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        try {
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packageName.replace(".", "/"));
            while (resources.hasMoreElements()) {
                File dir = new File(resources.nextElement().toURI());
                scanDir(dir, packageName, classes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }

    /**
     * 扫描目录
     *
     * @param file        文件
     * @param packageName 包名
     * @param classes     类集合
     * @throws ClassNotFoundException 类找不到异常
     */
    private static void scanDir(File file, String packageName, List<Class<?>> classes) throws ClassNotFoundException {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    scanDir(f, packageName + (f.isDirectory() ? "." + f.getName() : ""), classes);
                }
            }
        } else {
            String fileName = file.getName();
            if (fileName.endsWith(".class")) {
                String className = packageName + "." + fileName.substring(0, fileName.lastIndexOf(".class"));
                classes.add(Class.forName(className));
            }
        }
    }
}

/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.component;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zengyanyu.system.util.SpringContextUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * 自动同步表/字段注释到 PostgreSQL（支持父类 + 驼峰转下划线）
 *
 * @author zengyanyu
 */
@Component
@RequiredArgsConstructor
public class CommentSyncRunner implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    private static final String ENTITY_PACKAGE = "com.zengyanyu.system.entity";

    // 是否开启扫描添加注释
    private static final Boolean isEnabled = false;

    /**
     * @param args
     */
    @Override
    public void run(String... args) {
        if (isEnabled) {
            try {
                System.out.println("🚀 开始自动同步【表+字段+父类】注释...");
                List<Class<?>> classList = ClassScannerUtil.getClasses(ENTITY_PACKAGE);

                for (Class<?> clazz : classList) {
                    if (!clazz.isAnnotationPresent(TableName.class)) {
                        continue;
                    }
                    syncTableComment(clazz);
                    syncAllFields(clazz);
                }
                System.out.println("✅ 注释同步完成！");
            } catch (Exception e) {
                System.err.println("❌ 注释同步失败：" + e.getMessage());
            }
        }
    }

    /**
     * 同步表注释
     *
     * @param clazz
     */
    private void syncTableComment(Class<?> clazz) {
        TableName tableName = clazz.getAnnotation(TableName.class);
        ApiModel apiModel = clazz.getAnnotation(ApiModel.class);
        if (tableName == null || apiModel == null) {
            return;
        }

        String table = tableName.value();
        String comment = apiModel.value();

        // mysql
        if (!"dev".equals(SpringContextUtil.getProperty("spring.profiles.active"))) {
            if (StringUtils.hasText(comment)) {
                execute(String.format("ALTER TABLE %s COMMENT = '%s';", table, comment.replace("'", "''")));
            }
        } else {
            // postgresql
            if (StringUtils.hasText(comment)) {
                execute(String.format("COMMENT ON TABLE %s IS '%s';", table, comment.replace("'", "''")));
            }
        }
    }

    /**
     * 同步当前类 + 所有父类字段
     *
     * @param clazz
     */
    private void syncAllFields(Class<?> clazz) {
        TableName tableName = clazz.getAnnotation(TableName.class);
        if (tableName == null) {
            return;
        }
        String table = tableName.value();

        List<Field> allFields = getAllFields(clazz);

        for (Field field : allFields) {
            // 优先取 @Comment，没有则取 @ApiModelProperty
            Comment commentAnno = field.getAnnotation(Comment.class);
            ApiModelProperty apiModelAnno = field.getAnnotation(ApiModelProperty.class);

            String comment = null;
            if (commentAnno != null) {
                comment = commentAnno.value();
            } else if (apiModelAnno != null) {
                comment = apiModelAnno.value();
            }
            if (comment == null) {
                continue;
            }

            // 关键：驼峰转下划线 updateTime → update_time
            String columnName = camelToUnderline(field.getName());

            String sql = "";
            // MySQL 驱动
            if (!"dev".equals(SpringContextUtil.getProperty("spring.profiles.active"))) {
                String columnType = getMySQLColumnType(table, columnName);
                String safeComment = comment.replace("'", "''");
                if (columnType == null) {
                    continue;
                }

                // MySQL 标准语法：MODIFY COLUMN 字段 原类型 COMMENT '注释'
                sql = String.format(
                        "ALTER TABLE %s MODIFY COLUMN %s %s COMMENT '%s'",
                        table, columnName, columnType, safeComment
                );
            } else {
                // postgresql
                sql = String.format("COMMENT ON COLUMN %s.%s IS '%s';",
                        table, columnName, comment.replace("'", "''"));
            }
            execute(sql);
        }
    }

    /**
     * 查询 MySQL 字段真实类型
     *
     * @param table  表名称
     * @param column 列名称
     * @return
     */
    private String getMySQLColumnType(String table, String column) {
        try {
            String sql = "SELECT COLUMN_TYPE FROM information_schema.COLUMNS "
                    + "WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = ? AND COLUMN_NAME = ?";
            return jdbcTemplate.queryForObject(sql, String.class, table, column);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 【关键】驼峰 → 下划线
     *
     * @param str
     * @return
     */
    private String camelToUnderline(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 获取当前类 + 所有父类字段
     *
     * @param clazz
     * @return
     */
    private List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        while (clazz != null && clazz != Object.class) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    private void execute(String sql) {
        try {
            jdbcTemplate.execute(sql);
        } catch (DataAccessException ignored) {
        }
    }

    /**
     * 包扫描工具
     */
    public static class ClassScannerUtil {
        /**
         * @param packageName
         * @return
         */
        public static List<Class<?>> getClasses(String packageName) {
            List<Class<?>> classes = new ArrayList<>();
            try {
                Enumeration<URL> resources = Thread.currentThread().getContextClassLoader()
                        .getResources(packageName.replace(".", "/"));
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
         * @param file
         * @param packageName
         * @param classes
         * @throws ClassNotFoundException
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
}

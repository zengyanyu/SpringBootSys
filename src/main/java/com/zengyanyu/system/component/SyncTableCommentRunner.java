/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.component;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zengyanyu.system.util.ClassScannerUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 数据库注释自动同步（批量执行版，性能最优）
 * 支持 MySQL + PostgreSQL、父类字段、驼峰转下划线、批量执行SQL
 *
 * @author zengyanyu
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SyncTableCommentRunner implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final Environment environment;

    // 实体扫描包
    private static final String ENTITY_PACKAGE = "com.zengyanyu.system.entity";
    // 启用开关
    private static final boolean ENABLE_SYNC = false;
    // 环境标识 dev=PostgreSQL  其他=MySQL
    private static final String ENV_DEV = "dev";

    @Override
    public void run(String... args) {
        if (!ENABLE_SYNC) {
            log.info("====== 注释同步功能已关闭 ======");
            return;
        }

        log.info("====== 开始【批量执行】数据库注释同步 ======");
        try {
            // 1. 批量扫描实体类
            List<Class<?>> entityList = ClassScannerUtil.getClasses(ENTITY_PACKAGE);
            if (entityList.isEmpty()) {
                log.warn("未扫描到实体类，同步结束");
                return;
            }

            // 2. 批量收集所有SQL语句
            List<String> sqlBatchList = new ArrayList<>();
            for (Class<?> clazz : entityList) {
                collectTableAndFieldSql(clazz, sqlBatchList);
            }

            // 3. 批量执行所有SQL（核心：一次提交批量执行）
            if (!sqlBatchList.isEmpty()) {
                executeBatchSql(sqlBatchList);
                log.info("====== 批量执行完成，共执行SQL：{} 条 ======", sqlBatchList.size());
            }

        } catch (Exception e) {
            log.error("====== 批量注释同步执行失败 ======", e);
        }
    }

    /**
     * 收集单张表的 表注释SQL + 所有字段注释SQL（批量收集）
     */
    private void collectTableAndFieldSql(Class<?> clazz, List<String> sqlBatchList) {
        TableName tableName = clazz.getAnnotation(TableName.class);
        if (Objects.isNull(tableName) || StrUtil.isBlank(tableName.value())) {
            return;
        }
        String table = tableName.value();

        try {
            // 收集表注释SQL
            String tableSql = buildTableCommentSql(clazz, table);
            if (StrUtil.isNotBlank(tableSql)) {
                sqlBatchList.add(tableSql);
            }

            // 收集所有字段SQL
            collectFieldSqlList(clazz, table, sqlBatchList);

            log.info("已收集表【{}】的批量SQL", table);
        } catch (Exception e) {
            log.error("收集表【{}】SQL异常", table, e);
        }
    }

    /**
     * 构建表注释SQL
     */
    private String buildTableCommentSql(Class<?> clazz, String table) {
        ApiModel apiModel = clazz.getAnnotation(ApiModel.class);
        if (Objects.isNull(apiModel) || StrUtil.isBlank(apiModel.value())) {
            return null;
        }

        String comment = escapeSql(apiModel.value());
        boolean isPostgres = ENV_DEV.equals(environment.getProperty("spring.profiles.active"));

        if (isPostgres) {
            return String.format("COMMENT ON TABLE %s IS '%s';", table, comment);
        } else {
            // MySQL数据库
            return String.format("ALTER TABLE %s COMMENT = '%s';", table, comment);
        }
    }

    /**
     * 收集所有字段注释SQL（批量收集）
     */
    private void collectFieldSqlList(Class<?> clazz, String table, List<String> sqlBatchList) {
        List<Field> allFields = getAllFields(clazz);
        boolean isPostgres = ENV_DEV.equals(environment.getProperty("spring.profiles.active"));

        for (Field field : allFields) {
            String comment = getFieldComment(field);
            if (StrUtil.isBlank(comment)) {
                continue;
            }

            String column = camelToUnderline(field.getName());
            comment = escapeSql(comment);

            try {
                String fieldSql;
                if (isPostgres) {
                    fieldSql = String.format("COMMENT ON COLUMN %s.%s IS '%s';", table, column, comment);
                } else {
                    // MySQL 需要查询字段类型
                    String columnType = getMySqlColumnType(table, column);
                    if (StrUtil.isBlank(columnType)) {
                        continue;
                    }
                    fieldSql = String.format("ALTER TABLE %s MODIFY COLUMN %s %s COMMENT '%s';", table, column, columnType, comment);
                }
                sqlBatchList.add(fieldSql);
            } catch (Exception e) {
                log.warn("表【{}】字段【{}】构建SQL异常", table, column, e);
            }
        }
    }

    /**
     * 批量执行SQL（核心方法）
     *
     * @param sqlList SQL列表
     */
    private void executeBatchSql(List<String> sqlList) {
        try {
            jdbcTemplate.batchUpdate(sqlList.toArray(new String[0]));
        } catch (Exception e) {
            log.error("批量执行SQL失败", e);
        }
    }

    /**
     * 查询MySQL字段类型
     *
     * @param table  表名
     * @param column 列名
     * @return 字符串
     */
    private String getMySqlColumnType(String table, String column) {
        try {
            String sql = "SELECT COLUMN_TYPE FROM information_schema.COLUMNS " + " WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = ? AND COLUMN_NAME = ?;";

            return jdbcTemplate.queryForObject(sql, String.class, table, column);
        } catch (Exception e) {
            log.warn("查询表【{}】字段【{}】类型失败", table, column);
            return null;
        }
    }

    /**
     * 获取字段注释
     *
     * @param field 字段
     * @return 字符串
     */
    private String getFieldComment(Field field) {
        Comment comment = field.getAnnotation(Comment.class);
        if (Objects.nonNull(comment) && StrUtil.isNotBlank(comment.value())) {
            return comment.value();
        }

        ApiModelProperty api = field.getAnnotation(ApiModelProperty.class);
        if (Objects.nonNull(api) && StrUtil.isNotBlank(api.value())) {
            return api.value();
        }
        return null;
    }

    /**
     * 驼峰转下划线
     *
     * @param str 字符串
     * @return 字符串
     */
    private String camelToUnderline(String str) {
        if (StrUtil.isBlank(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append("_").append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 获取所有父类字段
     *
     * @param clazz 类
     * @return 字段列表
     */
    private List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        while (clazz != null && clazz != Object.class) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    /**
     * SQL单引号转义
     *
     * @param str 字符串
     * @return 字符串
     */
    private String escapeSql(String str) {
        return str == null ? "" : str.replace("'", "''");
    }
}
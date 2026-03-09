/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.config;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.querys.PostgreSqlQuery;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.zengyanyu.system.controller.BaseController;

import java.util.Collections;

/**
 * 代码生成器工具类
 *
 * @author zengyanyu
 */
public class CodeGenerator {

    private static final String dataBaseType = "mysql";

    // 系统跟目录
    private static final String ROOT_DIR = System.getProperty("user.dir");

    public static void main(String[] args) {
        codeGenerate("dict_copy1");
    }

    /**
     * 代码生成
     *
     * @param tableNames 表名集合（可变参数）
     */
    private static void codeGenerate(String... tableNames) {
        // 使用自定义entity模板
        FastAutoGenerator.create(getDataSourceConfig())
                // 全局配置
                .globalConfig(builder -> {
                    builder.author("zengyanyu")
                            .commentDate("yyyy-MM-dd")
                            .fileOverride()
                            // 指定输出目录
                            .outputDir(ROOT_DIR + "/src/main/java/")
                            // 生成代码后不自动打开目录
                            .disableOpenDir();
                })
                // 包配置
                .packageConfig(builder ->
                        // 设置父包名
                        builder.parent("com.zengyanyu")
                                // 设置父包模块名
                                .moduleName("system")
                                .entity("entity")
                                .service("service")
                                .serviceImpl("service.impl")
                                .mapper("mapper")
                                .controller("controller")
                                .xml("mapper.xml")
                                // 设置mapperXml生成路径
                                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, ROOT_DIR + "/src/main/resources/mapper/"))
                )
                // 策略配置
                .strategyConfig(builder -> {
                    // 设置需要生成的表名
                    builder.addInclude(tableNames);

                    // 建立Entity
                    builder.entityBuilder()
                            .idType(IdType.AUTO) // 主键类型（自增）
                            .formatFileName("%s") // 格式化实体名称，%s 表名占位符
//                            .enableTableFieldAnnotation()// 启用table字段注解，会显示@TableName
                            // 继承父类
//                            .superClass(BaseEntity.class)
                            // 不生成的字段（这4个字段，在BaseEntity中已经存在，不在子类中生成，使用继承的方式）
                            .addSuperEntityColumns("create_time", "create_by", "update_time", "update_by")
                            .enableLombok();

                    // 建立Mapper
                    builder.mapperBuilder()
                            // 启用Mapper注解
                            .enableMapperAnnotation();

                    // 建立Service
                    builder.serviceBuilder();

                    // 建立Controller
                    builder.controllerBuilder()
                            // 开启驼峰转连字符
                            .enableHyphenStyle()
                            .superClass(BaseController.class)
                            // 开启生成@RestController控制器
                            .enableRestStyle();

                    builder.build();
                })
                // 默认就是这个名称，可以不写
                .templateConfig(builder -> builder.entity("templates/entity.java"))
                // 默认的是Velocity引擎模板（默认）
                .templateEngine(new VelocityTemplateEngine())
                .execute();
    }

    /**
     * 使用PostGreSQL驱动
     *
     * @return
     */
    private static DataSourceConfig.Builder getDataSourceConfig() {
        if ("mysql".equals(dataBaseType)) {
            return new DataSourceConfig.Builder("jdbc:mysql://localhost:3306/hola?serverTimezone=GMT%2b8",
                    "root", "admin")
                    .dbQuery(new MySqlQuery());
        }
        return new DataSourceConfig.Builder("jdbc:postgresql://192.168.244.131:15432/test_sys",
                "postgres", "pgsql!@#12569088ht")
                .dbQuery(new PostgreSqlQuery());
    }

}

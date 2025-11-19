package com.zengyanyu.system.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
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

    public static void main(String[] args) {
        codeGenerate("algorithm_data",
                "algorithm_data_factor",
                "algorithm_data_factor_save",
                "algorithm_data_language",
                "algorithm_data_place",
                "algorithm_data_type");
    }

    /**
     * 代码生成
     *
     * @param tableNames 表名集合（可变参数）
     */
    private static void codeGenerate(String... tableNames) {
        // 使用自定义entity模板
        // 使用MySQL驱动
//        FastAutoGenerator.create(
//                "jdbc:mysql://localhost:3306/hola?serverTimezone=GMT%2b8",
//                "root",
//                "admin")
//        使用PostGreSQL驱动
        FastAutoGenerator.create(getDataSourceConfig())
                .globalConfig(builder -> {
                    builder.author("zengyanyu") // 设置作者
                            .commentDate("yyyy-MM-dd")
                            .enableSwagger() // 开启swagger模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:/Projects/SpringbootSys/src/main/java/"); // 指定输出目录
                })
                // 包配置
                .packageConfig(builder ->
                        builder.parent("com.zengyanyu.system") // 设置父包名
                                .moduleName(null) // 设置父包模块名
                                .controller("controller")// 设置控制器生成路径，他会自己拼接父包名
                                .pathInfo(Collections.singletonMap(OutputFile.mapperXml,
                                        "D:/Projects/SpringbootSys/src/main/resources/mapper/")) // 设置mapperXml生成路径
                )
                // 策略配置
                .strategyConfig(builder -> {
                    // 建立Mapper
                    builder.mapperBuilder().enableMapperAnnotation()// 启用Mapper注解
                            // .enableBaseResultMap()// 通用查询映射结果
                            // .enableBaseColumnList()// 通用查询结果列
                            .build();
                    // 建立Controller
                    builder.controllerBuilder().enableHyphenStyle() // 开启驼峰转连字符
                            .superClass(BaseController.class)
                            .enableRestStyle(); // 开启生成@RestController控制器

                    // 设置需要生成的表名
                    builder.addInclude(tableNames);
                    // 建立Entity
                    builder.entityBuilder()
//                            .enableTableFieldAnnotation()// 启用table字段注解，会显示@TableName
                            // 继承父类
//                            .superClass(BaseEntity.class)
                            // 不生成的字段（这4个字段，在BaseEntity中已经存在，不在子类中生成，使用继承的方式）
                            .addSuperEntityColumns("create_time", "create_by", "update_time", "update_by")
                            .enableLombok();
                })
                .templateConfig(builder -> builder.entity("templates/entity.java"))
                // 默认的是Velocity引擎模板
                .templateEngine(new VelocityTemplateEngine())
                .execute();
    }

    /**
     * 使用PostGreSQL驱动
     * @return
     */
    private static DataSourceConfig.Builder getDataSourceConfig() {
        return new DataSourceConfig.Builder("jdbc:postgresql://192.168.244.131:15432/test_sys",
                "postgres", "pgsql!@#12569088ht")
                .dbQuery(new PostgreSqlQuery());
    }

}

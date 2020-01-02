package com.example.clover.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/9/4 16:49
 * Created by lifan
 */
public class MpGeneratorUtils {

    /**
     * @param sourcePath
     * @param basePackage
     * @param tableNames
     */
    public static void generate(String sourcePath, String basePackage, String[] tableNames) {
        //全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(false)
                .setAuthor("Robot")
                .setOutputDir(sourcePath)
                .setServiceName("%sApi")
                .setServiceImplName("%sService")
                .setMapperName("%sDao")
                .setXmlName("%sDao")
                .setOpen(false)
                .setEnableCache(false)
                //是否覆盖已经生成的文件
                .setFileOverride(false);

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://94.191.42.28:3306/xiaoka2?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true")
                .setUsername("root")
                .setPassword("XiaoKaQuan666");
//        dataSourceConfig.setDbType(DbType.H2)
//                .setDriverName("org.h2.Driver")
//                .setUrl("jdbc:h2:mem:example")
//                .setUsername("example")
//                .setPassword("123456");

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel)
//                .setCapitalMode(false)
//                .setDbColumnUnderline(true)
//                .setTablePrefix("ec_")
                .entityTableFieldAnnotationEnable(true)
                .setRestControllerStyle(true)
                .setEntityLombokModel(false)
//                .setSuperEntityClass("com.deepinsight.club.common.base.BaseEntity")
//                .setSuperEntityColumns("id", "uid", "create_user_id", "create_date", "last_update_user_id", "last_update_date")
//                .setSuperServiceClass("")
//                .setSuperServiceImplClass("com.deepinsight.club.common.base.BaseService")
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(basePackage)
                .setController("controller")
                .setService("api")
                .setServiceImpl("service")
                .setMapper("dao")
                .setXml("dao")
                .setEntity("entity");

        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setController("/template/controller.java.vm")
                .setEntity("/template/entity.java.vm")
                .setMapper("/template/mapper.java.vm")
                .setXml("/template/mapper.xml.vm")
                .setService("/template/service.java.vm")
                .setServiceImpl("/template/serviceImpl.java.vm");

        //自定义文件输出位置（非必须）
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };


        String apiSourcePath = sourcePath.replace("-service", "-api") + basePackage.replaceAll("\\.", "/");
        List<FileOutConfig> fileOutList = new ArrayList<>();
        fileOutList.add(new FileOutConfig("/template/entity.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return apiSourcePath + "/entity/" + tableInfo.getEntityName() + ".java";
            }
        });
        fileOutList.add(new FileOutConfig("/template/service.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {

                return apiSourcePath + "/api/" + tableInfo.getEntityName() + "Api.java";
            }
        });

        injectionConfig.setFileOutConfigList(fileOutList);

        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setTemplate(templateConfig)
                .setCfg(injectionConfig)
                .execute();

        String originSourcePath = sourcePath + basePackage.replaceAll("\\.", "/");
        //覆盖api模块中的同名entity
        try {
            FileSystemUtils.copyRecursively(new File(originSourcePath + "/entity/"),
                    new File(apiSourcePath + "/entity/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //删除service模块下的entity和api
        FileSystemUtils.deleteRecursively(new File(originSourcePath + "/entity/"));
        FileSystemUtils.deleteRecursively(new File(originSourcePath + "/api/"));
    }

}

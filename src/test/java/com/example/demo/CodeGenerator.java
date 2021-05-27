package com.example.demo;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;

public class CodeGenerator {

    @Test
    public void genCode() {
        // 表前缀
        String prefix = "te_";
        // 业务名称
        String moduleName = "modules";

        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        // 当前项目的路径
        gc.setOutputDir("D:\\www\\Java\\springboot\\src\\test\\java\\com\\generator");
        gc.setAuthor("Mr.monster.liu");
        gc.setOpen(true); //生成后是否打开资源管理器
        gc.setFileOverride(true); //重新生成时文件是否覆盖
        gc.setServiceName("%sService");    //去掉Service接口的首字母I
        gc.setIdType(IdType.AUTO); //主键策略
        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
        gc.setSwagger2(true);//开启Swagger2模式
        gc.setBaseColumnList(true);//字段列
        gc.setBaseResultMap(true);//映射结果集
        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://8.136.117.38:3306/demo?useUnicode=true&characterEncoding=utf8&useSSL=false");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName); //模块名
        pc.setParent("com.example.demo");
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);//underline_to_camel数据库表映射到实体的命名策略
        strategy.setInclude("te_users");//生成指定的表，不设置则生成数据库所有表
        strategy.setTablePrefix(moduleName + "_");//设置表前缀不生成
        strategy.setColumnNaming(NamingStrategy.no_change);//no_change不改变数据库命名
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

        // strategy.setLogicDeleteFieldName("is_deleted");//逻辑删除字段名
        // strategy.setEntityBooleanColumnRemoveIsPrefix(true);//去掉布尔值的is_前缀

        //自动填充
        // TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        // TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        // ArrayList<TableFill> tableFills = new ArrayList<>();
        // tableFills.add(gmtCreate);
        // tableFills.add(gmtModified);
        // strategy.setTableFillList(tableFills);

        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符
        mpg.setStrategy(strategy);

        //设置BaseEntity
        // strategy.setSuperEntityClass("com.summersky.guli.service.base.model.BaseEntity");
        // 填写BaseEntity中的公共字段
        // strategy.setSuperEntityColumns("id", "gmt_create", "gmt_modified");

        // 6、执行
        mpg.execute();
    }
}

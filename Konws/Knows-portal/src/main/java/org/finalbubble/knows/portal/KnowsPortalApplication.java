package org.finalbubble.knows.portal;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// Generated by https://start.springboot.io
// 优质的 spring/boot/data/security/cloud 框架中文文档尽在 => https://springdoc.cn

//SpringBoot启动类是SpringBoot框架自带的Spring配置类
//具有之前Spring框架XXXConfig类的功能
//SpringBoot项目会默认扫描SpringBoot启动类所在的包
//相当于ComponentScan("org.finalbubble.knows.portal")
@SpringBootApplication
// 下面注解的含义是令Mybatis框架扫描指定包中的所有接口和类
// 相当于为指定包中所有类和接口都添加了@Mapper注解
@MapperScan("org.finalbubble.knows.portal.mapper")
public class KnowsPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnowsPortalApplication.class, args);
    }

}

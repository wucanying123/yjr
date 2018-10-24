package com.pt1002;


import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Properties;


@EnableFeignClients
@EnableDiscoveryClient
@EnableScheduling
@MapperScan(basePackages = "com.pt1002.modules.mapper")
@SpringBootApplication
public class Pt1002Application {

    public static void main(String[] args) {
        SpringApplication.run(Pt1002Application.class, args);
    }

    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");//多余的数据是否作为新的页
        properties.setProperty("rowBoundsWithCount","true");//读取总数
        properties.setProperty("reasonable","true");//
        properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
        pageHelper.setProperties(properties);
        return pageHelper;
    }

}

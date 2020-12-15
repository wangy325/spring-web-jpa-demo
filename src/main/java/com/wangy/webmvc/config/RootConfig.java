package com.wangy.webmvc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/14 / 12:03
 */

@Configuration
@ComponentScan(basePackages = "com.wangy.webmvc.*", excludeFilters = {
    @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class),
    @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)})
@PropertySource("classpath:application.properties")
public class RootConfig extends AnnotationConfigApplicationContext {

    {
        System.out.println("load RootConfig");
    }

    @Value("${embed.driver}")
    public String h2Driver;
    @Value("${embed.url}")
    public String h2Url;
    @Value("${mysql.driver}")
    public String mysqlDriver;
    @Value("${mysql.url}")
    public String mysqlUrl;
    @Value("${spring.datasource.username}")
    public String username;
    @Value("${spring.datasource.password}")
    public  String password;
    @Value("${spring.datasource.schema}")
    public String h2Schema;
    @Value("${spring.datasource.data}")
    public String h2Data;
    @Value("${spring.profiles.active}")
    public String[] activeProfiles;
    @Value("${spring.persistenceType}")
    public String persistenceType;

}

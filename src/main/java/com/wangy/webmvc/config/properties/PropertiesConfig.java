package com.wangy.webmvc.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/12/24 / 21:56
 */
@Configuration
public class PropertiesConfig {

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
    public String password;
    @Value("${spring.datasource.schema}")
    public String h2Schema;
    @Value("${spring.datasource.data}")
    public String h2Data;
    @Value("${hibernate.properties}")
    public String hibernateProperties;
    @Value("${hibernate.entity.package}")
    public String entityPackage;
    @Value("${hibernate.JpaVendorAdapter.h2.dialect}")
    public String jpaH2Dialect;
    @Value("${hibernate.JpaVendorAdapter.mysql.dialect}")
    public String jpaMysqlDialect;
}

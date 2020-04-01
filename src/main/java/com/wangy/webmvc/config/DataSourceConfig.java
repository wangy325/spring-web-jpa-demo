package com.wangy.webmvc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/23 / 22:57
 */
@Configuration
@PropertySource("classpath:application.properties")
public class DataSourceConfig {

    @Value("${embed.driver}")
    private String h2Driver;
    @Value("${embed.url}")
    private String h2Url;
    @Value("${mysql.driver}")
    private String mysqlDriver;
    @Value("${mysql.url}")
    private String mysqlUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.schema}")
    private String h2Schema;
    @Value("${spring.datasource.data}")
    private String h2Data;


    @Bean
    @Profile("dev0")
    public DataSource embeddedDataSource_0() {
        //以下配置使用Spring jdbc配置嵌入式数据库
        //使用h2数据库默认配置(testdb,sa,empty)并运行指定脚本
        //脚本可自动配置，无需指定
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            /*.addScript(h2Schema)
            .addScript(h2Data)*/
            .build();
    }


    @Bean
    @Profile("dev1")
    public DataSource embeddedDataSource_1() {
        // 以下配置使用hikari连接池接入h2内存数据库
        // TODO 脚本自动配置运行 ？
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(h2Url);
        hikariConfig.setDriverClassName(h2Driver);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setConnectionTimeout(3000);
        hikariConfig.setMinimumIdle(2);
        return new HikariDataSource(hikariConfig);
    }


    @Bean
    @Profile("qa")
    public DataSource dataSource() {
        // 简单的hikari数据库连接池配置
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(mysqlUrl);
        hikariConfig.setDriverClassName(mysqlDriver);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        return new HikariDataSource(hikariConfig);
    }
}

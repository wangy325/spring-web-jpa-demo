package com.wangy.webmvc.config.datasource;

import com.wangy.webmvc.config.properties.PropertiesConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/23 / 22:57
 */
@Configuration
public class DataSourceConfig {

    @Autowired
    public PropertiesConfig propertiesConfig;


    @Bean
    @Profile("h2")
    public DataSource springEmbedH2() {
        //以下配置使用Spring jdbc配置嵌入式数据库
        //使用h2数据库默认配置(testdb,sa,empty)并运行指定脚本
        //(springboot)脚本可自动配置，无需指定
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript(propertiesConfig.h2Schema)
            .addScript(propertiesConfig.h2Data)
            .build();
    }


    @Bean
    @Profile("h2-hikari")
    public DataSource hikariEmbedH2() {
        // 以下配置使用hikari连接池接入h2内存数据库
        // TODO 脚本自动配置运行 ？
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(propertiesConfig.h2Url);
        hikariConfig.setDriverClassName(propertiesConfig.h2Driver);
        hikariConfig.setUsername(propertiesConfig.username);
        hikariConfig.setPassword(propertiesConfig.password);
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setConnectionTimeout(3000);
        hikariConfig.setMinimumIdle(2);
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    @Profile("mysql-hikari")
    public DataSource hikariMysql() {
        // 简单的hikari数据库连接池配置
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(propertiesConfig.mysqlUrl);
        hikariConfig.setDriverClassName(propertiesConfig.mysqlDriver);
        hikariConfig.setUsername(propertiesConfig.username);
        hikariConfig.setPassword(propertiesConfig.password);
        return new HikariDataSource(hikariConfig);
    }
}

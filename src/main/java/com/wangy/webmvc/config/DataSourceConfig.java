package com.wangy.webmvc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
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


    @Bean
    @Profile("dev")
    public DataSource embeddedDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(h2Url);
        hikariConfig.setDriverClassName(h2Driver);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        return new HikariDataSource(hikariConfig);
        /*return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript(h2Schema)
            .addScript(h2Data)
            .build();*/
    }

    @Bean
    @Profile("qa")
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(mysqlUrl);
        hikariConfig.setDriverClassName(mysqlDriver);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        return new HikariDataSource(hikariConfig);
    }
}

package com.wangy.webmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


/**
 * 使用Spring JDBC提供的{@link JdbcTemplate}来减少Java JDBC的样板式代码（连接获取，异常处理，资源释放...）
 *
 * @author wangy
 * @version 1.0
 * @date 2020/3/20 / 14:57
 */
@Configuration
@EnableTransactionManagement
public class JdbcConfig {

    private final DataSource dataSource;

    public JdbcConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public PlatformTransactionManager jdbcTransactionManager(){
        return new DataSourceTransactionManager(dataSource);
    }
}

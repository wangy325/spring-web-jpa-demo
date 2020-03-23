package com.wangy.webmvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/20 / 14:57
 */
@Configuration
public class JdbcConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource);
    }
}

package com.wangy.webmvc.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/17 / 19:41
 */
@Configuration
@EnableTransactionManagement
//@ComponentScan
public class HibernateConfig {

    private final DataSource dataSource;

    public HibernateConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        lsfb.setDataSource(dataSource);
        // 必须使用注解配置Hibernate元数据
        lsfb.setPackagesToScan("com.wangy.webmvc.data.bean");
        Properties properties = new Properties();
        properties.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
        lsfb.setHibernateProperties(properties);
        try {
            lsfb.afterPropertiesSet();
        } catch (IOException e) {
            return null;
        }
        return lsfb.getObject();
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory());
        return transactionManager;
    }

}

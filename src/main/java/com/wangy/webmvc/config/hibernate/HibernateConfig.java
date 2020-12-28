package com.wangy.webmvc.config.hibernate;

import com.wangy.webmvc.config.condition.PersistenceType;
import com.wangy.webmvc.config.properties.PropertiesConfig;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 基于spring-orm的hibernate sessionFactory 配置和事务配置
 *
 * @author wangy
 * @version 1.0
 * @date 2020/3/17 / 19:41
 * @see LocalSessionFactoryBean
 */
@Configuration
@EnableTransactionManagement
@PersistenceType("hibernate")
public class HibernateConfig {

    @Autowired
    public PropertiesConfig propertiesConfig;
    @Autowired
    private DataSource dataSource;


    @Bean
    public SessionFactory hibernateSessionFactory() {
        // 使用Spring-orm创建Hibernate SessionFactory
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        // 必须使用基于注解配置的Hibernate元数据
        localSessionFactoryBean.setPackagesToScan(propertiesConfig.entityPackage);
        try {
            // many ways to load a property file
            InputStream inputStream = new ClassPathResource(propertiesConfig.hibernateProperties)
                .getInputStream();
            Properties properties = new Properties();
            properties.load(inputStream);
            localSessionFactoryBean.setHibernateProperties(properties);
            localSessionFactoryBean.afterPropertiesSet();
        } catch (IOException e) {
            return null;
        }
        return localSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

}

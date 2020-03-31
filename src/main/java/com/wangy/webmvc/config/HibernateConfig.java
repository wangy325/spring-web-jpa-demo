package com.wangy.webmvc.config;

import org.hibernate.SessionFactory;
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
//@Configuration
//@EnableTransactionManagement
public class HibernateConfig {

    private final DataSource dataSource;

    public HibernateConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

//    @Bean
    public SessionFactory sessionFactory() {
        // 使用Spring-orm创建Hibernate SessionFactory
        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        lsfb.setDataSource(dataSource);
        // 必须使用注解配置Hibernate元数据
        lsfb.setPackagesToScan("com.wangy.webmvc.data.bean");
        try {
            // many ways to load a property file
            ClassPathResource resource = new ClassPathResource("hibernate/hibernate.properties");
            InputStream inputStream = resource.getInputStream();

//            ResourceBundle bundle = ResourceBundle.getBundle("hibernate/hibernate.properties");

            InputStream stream = ClassLoader.getSystemResourceAsStream("hibernate/hibernate.properties");
            Properties properties = new Properties();
            properties.load(stream);
            lsfb.setHibernateProperties(properties);
            lsfb.afterPropertiesSet();
        } catch (IOException e) {
            return null;
        }
        return lsfb.getObject();
    }

//    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory());
        return transactionManager;
    }

}

package com.wangy.webmvc.config.jpa;

import com.wangy.webmvc.config.condition.JpaDatabaseType;
import com.wangy.webmvc.config.condition.PersistenceType;
import com.wangy.webmvc.config.properties.PropertiesConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.springframework.orm.jpa.vendor.Database.H2;
import static org.springframework.orm.jpa.vendor.Database.MYSQL;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/29 / 19:00
 */
@Configuration
@EnableTransactionManagement
@PersistenceType("jpa")
public class JpaConfig {

    private DataSource dataSource;
    private PropertiesConfig propertiesConfig;

    public JpaConfig(DataSource dataSource, PropertiesConfig propertiesConfig) {
        this.dataSource = dataSource;
        this.propertiesConfig = propertiesConfig;
    }

    /**
     * 使用{@link HibernateJpaVendorAdapter}作为JPA的实现
     *
     * @return JpaVendorAdapter
     */
    @Bean
    @JpaDatabaseType
    public JpaVendorAdapter jpaVendorAdapterH2() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabase(H2);
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(false);
        hibernateJpaVendorAdapter.setDatabasePlatform(propertiesConfig.jpaH2Dialect);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    @JpaDatabaseType("mysql")
    public JpaVendorAdapter jpaVendorAdapterMysql() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabase(MYSQL);
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(false);
        hibernateJpaVendorAdapter.setDatabasePlatform(propertiesConfig.jpaMysqlDialect);
        return hibernateJpaVendorAdapter;
    }

    /**
     * 使用spring支持的jpa EntityManager配置，可以完全摆脱persistence.xml
     *
     * @return {@link EntityManager}
     */
    @Bean
    @SuppressWarnings("all")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        // mapper scan
        entityManagerFactoryBean.setPackagesToScan(propertiesConfig.entityPackage);
        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager jpaTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setDataSource(dataSource);
        return jpaTransactionManager;
    }

}

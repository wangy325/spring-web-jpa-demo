package com.wangy.webmvc.config.jpa;

import com.wangy.webmvc.config.RootConfig;
import com.wangy.webmvc.config.condition.JpaDatabaseType;
import com.wangy.webmvc.config.condition.PersistenceType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
    private RootConfig rootConfig;

    public JpaConfig(DataSource dataSource, RootConfig rootConfig) {
        this.dataSource = dataSource;
        this.rootConfig = rootConfig;
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
        hibernateJpaVendorAdapter.setDatabasePlatform(rootConfig.jpaH2Dialect);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    @JpaDatabaseType("mysql")
    public JpaVendorAdapter jpaVendorAdapterMysql() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabase(MYSQL);
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(false);
        hibernateJpaVendorAdapter.setDatabasePlatform(rootConfig.jpaMysqlDialect);
        return hibernateJpaVendorAdapter;
    }

    /**
     * 使用spring支持的jpa EntityManager配置，可以完全摆脱persistence.xml
     *
     * @return {@link javax.persistence.EntityManager}
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        // mapper scan
        entityManagerFactoryBean.setPackagesToScan(rootConfig.entityPackage);
        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager jpaTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setDataSource(dataSource);
        return jpaTransactionManager;
    }

}

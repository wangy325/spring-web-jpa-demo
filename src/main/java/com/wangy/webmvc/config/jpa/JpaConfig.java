package com.wangy.webmvc.config.jpa;

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

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/29 / 19:00
 */
@Configuration
@EnableTransactionManagement
public class JpaConfig {

    private final DataSource dataSource;

    public JpaConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 使用{@link HibernateJpaVendorAdapter}作为JPA的实现
     *
     * @return JpaVendorAdapter
     */
    @Bean
    @PersistenceType("jpa")
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hjva = new HibernateJpaVendorAdapter();
        hjva.setDatabase(H2);
        hjva.setShowSql(true);
        hjva.setGenerateDdl(false);
//        hjva.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
        return hjva;
    }

    /**
     * 使用spring支持的jpa EntityManager配置，可以完全摆脱persistence.xml
     *
     * @return {@link javax.persistence.EntityManager}
     */
    @Bean
    @PersistenceType("jpa")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        // mapper scan
        emfb.setPackagesToScan("com.wangy.webmvc.data.bean");
        return emfb;
    }

    @Bean
    @PersistenceType("jpa")
    public PlatformTransactionManager jpaTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setDataSource(dataSource);
        return jpaTransactionManager;
    }

}

package com.wangy.webmvc.config;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Objects;
import java.util.Properties;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/14 / 12:03
 * TODO: is @PropertySource really needed?
 */

@Configuration
@ComponentScan(basePackages = "com.wangy.webmvc.*", excludeFilters = {
    @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class),
    @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)})
@PropertySource("classpath:application.yml")
public class RootConfig extends AnnotationConfigApplicationContext {


    @Bean
    public YamlPropertiesFactoryBean yamlPropertiesFactoryBean(){
        YamlPropertiesFactoryBean yamlProperty = new YamlPropertiesFactoryBean();
        yamlProperty.setResources(new ClassPathResource("application.yml"));
        return  yamlProperty;
    }


    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(YamlPropertiesFactoryBean yamlPropertiesFactoryBean) {
        // 配置 PropertyPlaceholder
        PropertySourcesPlaceholderConfigurer yamlPropertyPlaceholder =
            new PropertySourcesPlaceholderConfigurer();
        yamlPropertyPlaceholder.setProperties(Objects.requireNonNull(yamlPropertiesFactoryBean.getObject()));
        yamlPropertyPlaceholder.setFileEncoding("UTF-8");
        return yamlPropertyPlaceholder;
    }
}

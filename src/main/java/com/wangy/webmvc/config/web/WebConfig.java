package com.wangy.webmvc.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Dispatcher Servlet 配置类
 *
 * @author wangy
 * @version 1.0
 * @date 2020/3/14 / 12:03
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.wangy.webmvc.controller")
public class WebConfig implements WebMvcConfigurer {

    /**
     * 配置JSP视图解析器
     *
     * @return ViewResolver
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//         要求DispatcherServlet对静态资源的请求转发到默认的Servlet中，而不是DispatcherServlet处理此类请求
        configurer.enable();
    }
}

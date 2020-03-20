package com.wangy.webmvc.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 代替web.xml的配置类，用来配置web容器的上下文
 *
 * @author wangy
 * @version 1.0
 * @date 2020/3/14 / 11:58
 * @see org.springframework.web.WebApplicationInitializer
 * @see org.springframework.web.SpringServletContainerInitializer
 * @see javax.servlet.ServletContainerInitializer
 */
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 配置ContextLoaderListener
     *
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    /**
     * 配置DispatcherServlet
     *
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    /**
     * 将dispatcherServlet映射到"/"
     *
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}

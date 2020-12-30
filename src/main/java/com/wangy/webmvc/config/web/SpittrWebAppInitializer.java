package com.wangy.webmvc.config.web;

import com.wangy.webmvc.config.RootConfig;
import com.wangy.webmvc.config.swagger.SwaggerConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

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
     * 配置ContextLoaderListener，加载项目在{@link RootConfig}中定义的组建，如由@Repository
     * ，@Component声明的Bean，以及一些后端配置组建
     *
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    /**
     * 配置DispatcherServlet， 让其使用在{@link WebConfig}中定义的Bean，即Controller，
     * 视图解析器以及处理映射器等其他组建
     *
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class, SwaggerConfig.class};
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


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

        // 配置额外的h2数据库控制台Servlet
        ServletRegistration.Dynamic H2Console =
            servletContext.addServlet("h2Console", "org.h2.server.web.WebServlet");
        H2Console.setLoadOnStartup(1);
        H2Console.addMapping("/h2/*");
    }
}

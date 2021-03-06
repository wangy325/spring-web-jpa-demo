package com.wangy.webmvc.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加EnableWebMvc注解是为了单元测试通过
 *
 * @author wangy
 * @version 1.0
 * @date 2020/12/29 / 22:05
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {

    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.wangy.webmvc.controller"))
            .paths(PathSelectors.any())
            .build()
            .securitySchemes(securitySchemes())
            .securityContexts(securityContexts())
            ;
    }

    /**
     * 为swagger添加统一认证参数<br>
     * https://www.jianshu.com/p/7a24d202b395
     */
    private List<ApiKey> securitySchemes() {
        return new ArrayList<ApiKey>() {{
            add(new ApiKey("Authorization", "Authorization", "header"));
        }};
    }

    private List<SecurityContext> securityContexts() {
        return new ArrayList<SecurityContext>() {{
            add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build());
        }};
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return new ArrayList<SecurityReference>() {{
            add(new SecurityReference("Authorization", authorizationScopes));
        }};
    }


    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 默认访问地址：http://host:port/swagger-ui.html
     *
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Spitter Application API")
            .description("《spring in action》学习项目API文档")
            .termsOfServiceUrl("http://localhost:8080/swagger-ui.html")
            .version("1.0")
            .build();
    }
}

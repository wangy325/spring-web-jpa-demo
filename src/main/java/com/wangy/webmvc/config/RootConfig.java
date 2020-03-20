package com.wangy.webmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/14 / 12:03
 */

@Configuration
@ComponentScan(basePackages = "com.wangy.webmvc.*", excludeFilters = {
    @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class),
    @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)})
public class RootConfig {


}

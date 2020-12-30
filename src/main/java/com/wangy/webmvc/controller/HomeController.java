package com.wangy.webmvc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/14 / 13:28
 */
@Controller
@RequestMapping({"/","/homepage","home"})
@Api("主页控制器")
public class HomeController {

    @RequestMapping(method = GET)
    @ApiOperation("首页请求")
    public String home(){
        return "home";
    }
}

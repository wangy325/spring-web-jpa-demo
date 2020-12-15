package com.wangy.webmvc.controller;

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
public class HomeController {

    @RequestMapping(method = GET)
    public String home(){
        return "home";
    }
}

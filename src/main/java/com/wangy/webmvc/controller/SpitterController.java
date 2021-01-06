package com.wangy.webmvc.controller;

import com.wangy.webmvc.entity.Spitter;
import com.wangy.webmvc.model.dto.SpitterDTO;
import com.wangy.webmvc.service.SpitterRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/16 / 12:01
 */

@Api(tags = {"用户控制器"})
@Controller
@RequestMapping("/spitter")
@SuppressWarnings("all")
public class SpitterController {

    private SpitterRepository spitterRepository;

    @Autowired
    public SpitterController(SpitterRepository spitterRepository) {
        this.spitterRepository = spitterRepository;
    }

    @ApiOperation("获取注册页面")
    @RequestMapping(value = "/register", method = GET)
    public String showRegisterPage() {
        return "registerForm";
    }

    /**
     * here is a issue about hidden params do not need:
     * <a href="https://github.com/springfox/springfox/issues/1438">
     * https://github.com/springfox/springfox/issues/1438
     * </a>
     *
     * @param spitterDTO
     * @param errors
     * @return
     */
    @ApiOperation("注册新用户")
    @RequestMapping(value = "/register", method = POST)
    public String register(@Valid SpitterDTO spitterDTO,
                           @ApiIgnore @ApiParam(value = "参数检验异常", hidden = true) Errors errors) {
        if (errors.hasErrors()) {
            return "registerForm";
        }
        Spitter spitter = new Spitter();
        BeanUtils.copyProperties(spitterDTO, spitter);
        Spitter save = spitterRepository.save(spitter);
        return "redirect:/spitter/" + spitter.getUsername();
    }

    @ApiOperation(value = "获取指定用户信息", response = String.class)
    @RequestMapping(value = "/{username}", method = GET)
    public String showProfile(@PathVariable @ApiParam(value = "用户名", required = true) String username,
                              Model model) {
        model.addAttribute(spitterRepository.findByUsername(username));
        return "profile";
    }
}

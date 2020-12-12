package com.wangy.webmvc.controller;

import com.wangy.webmvc.data.SpitterRepository;
import com.wangy.webmvc.data.bean.Spitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/16 / 12:01
 */
@Controller
@RequestMapping("/spitter")
public class SpitterController {

    private SpitterRepository spitterRepository;

    @Autowired
    public SpitterController(SpitterRepository spitterRepository) {
        this.spitterRepository = spitterRepository;
    }

    @RequestMapping(value = "/register", method = GET)
    public String showRegisterPage() {
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = POST)
    public String register(@Valid Spitter spitter, Errors errors) {
        if (errors.hasErrors()){
            return "registerForm";
        }
        Spitter save = spitterRepository.save(spitter);
        return "redirect:/spitter/" + spitter.getUsername();
    }

    @RequestMapping(value = "/{username}", method = GET)
        public String showProfile(@PathVariable String username, Model model) {
            model.addAttribute(spitterRepository.findByUsername(username));
            return "profile";
    }
}

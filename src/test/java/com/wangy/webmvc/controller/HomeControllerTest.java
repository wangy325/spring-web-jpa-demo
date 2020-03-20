package com.wangy.webmvc.controller;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/14 / 16:52
 */
public class HomeControllerTest {

    @Test
    public void testHomePage() throws Exception {
        HomeController hc =  new HomeController();
        MockMvc mockMvc = standaloneSetup(hc).build();

        mockMvc.perform(get("/")).andExpect(view().name("home"));
    }
}

package com.wangy.webmvc.controller;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

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
        MockMvc mockMvc = standaloneSetup(hc)
            // Circular view path [spittles]: would dispatch back to the current handler URL [/spittles] again.
            // Check your ViewResolver setup! (Hint: This may be the result of an unspecified view,
            // due to default view name generation.)
            // 设置SingleView是因为url路径和视图名字相同了，一般情况下无需此设置
            .setSingleView(new InternalResourceView("/WEB-INF/views/home.jsp"))
            .build();

        mockMvc.perform(get("/home"))
            .andExpect(view().name("home"));
    }
}

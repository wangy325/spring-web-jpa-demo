package com.wangy.webmvc.controller;

import com.wangy.webmvc.data.SpitterRepository;
import com.wangy.webmvc.data.bean.Spitter;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/16 / 12:08
 */
public class SpitterControllerTest {

    @Test
    public void testShowRegisterPage() throws Exception {
        SpitterController spitterController = new SpitterController(null);
        MockMvc mockMvc = standaloneSetup(spitterController).build();
        mockMvc.perform(get("/spitter/register")).andExpect(view().name("registerForm"));
    }

    @Test
    public void testRegister() throws Exception {
        SpitterRepository spitterRepository = mock(SpitterRepository.class);
        Spitter unsaved = new Spitter("Jack", "Bauer", "jbauer", "24hours");
        Spitter saved = new Spitter(11, "Jack", "Bauer", "jbauer", "24hours");
        when(spitterRepository.save(unsaved)).thenReturn(saved);
        SpitterController spitterController = new SpitterController(spitterRepository);
        MockMvc mockMvc = standaloneSetup(spitterController).build();
        // 使用mockmvc发起post请求，并配置请求参数
        mockMvc.perform(post("/spitter/register")
            .param("firstName", "Jack")
            .param("lastName", "Bauer")
            .param("username", "jbauer")
            .param("password", "24hours"))
            .andExpect(redirectedUrl("/spitter/jbauer"));
        verify(spitterRepository, atLeastOnce()).save(unsaved);

    }
}

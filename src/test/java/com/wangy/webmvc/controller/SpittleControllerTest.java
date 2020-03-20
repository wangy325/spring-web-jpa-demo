package com.wangy.webmvc.controller;

import com.wangy.webmvc.data.SpittleRepository;
import com.wangy.webmvc.data.bean.Spittle;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/14 / 22:04
 */
public class SpittleControllerTest {

    @Test
    public void testGetRecentSpittles() throws Exception {
        List<Spittle> expectSpittles = createSpittles(20);
        // mock是创建基于接口的实现类
        SpittleRepository mockRepository = mock(SpittleRepository.class);
        when(mockRepository.getSpittles(Long.MAX_VALUE, 20)).thenReturn(expectSpittles);
        SpittleController spittleController = new SpittleController(mockRepository);
        //使用MocMvc测试不必启动mvc容器
        MockMvc mockMvc = standaloneSetup(spittleController).setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")).build();
//        mockMvc.perform(get("/spittles/page"))
        mockMvc.perform(get("/spittles"))
            .andExpect(view().name("spittles"))
            .andExpect(model().attributeExists("spittleList"))
            .andExpect(model().attribute("spittleList", hasItems(expectSpittles.toArray())));
    }

    @Test
    public void testGetPageSpittle() throws Exception {
        List<Spittle> expectSpittles = createSpittles(1234L, 10);
        SpittleRepository mockRepository = mock(SpittleRepository.class);
        when(mockRepository.getSpittles(1234L, 10)).thenReturn(expectSpittles);
        SpittleController spittleController = new SpittleController(mockRepository);
        MockMvc mockMvc = standaloneSetup(spittleController).build();
        mockMvc.perform(get("/spittles/page?maxId=1234&count=10"))
            .andExpect(model().attributeExists("spittleList"))
            .andExpect(model().attribute("spittleList", hasItems(expectSpittles.toArray())));
    }

    @Test
    public void testGetOneSpittle() throws Exception {
        Spittle expectSpittle = new Spittle("spittle with id 1234", new Date());
        SpittleRepository spittleRepository = mock(SpittleRepository.class);
        when(spittleRepository.findOne(1234)).thenReturn(expectSpittle);
        SpittleController spittleController = new SpittleController(spittleRepository);
        MockMvc mockMvc = standaloneSetup(spittleController).build();

        mockMvc.perform(get("/spittles/1234"))
            .andExpect(model().attributeExists("spittle"))
            .andExpect(model().attribute("spittle", expectSpittle));
    }

    private List<Spittle> createSpittles(int count) {
        List<Spittle> spittleList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            spittleList.add(new Spittle("spittle" + i, new Date()));
        }
        return spittleList;
    }

    private List<Spittle> createSpittles(Long id, int count) {
        List<Spittle> spittleList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            spittleList.add(new Spittle(--id, "spittle" + i, new Date()));
        }
        return spittleList;
    }
}

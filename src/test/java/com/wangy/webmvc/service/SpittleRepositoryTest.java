package com.wangy.webmvc.service;

import com.wangy.webmvc.config.RootConfig;

import com.wangy.webmvc.entity.Spittle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/26 / 00:19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@ActiveProfiles("h2")
@TestPropertySource(properties = {"persistenceType=jdbc"})
@Transactional(rollbackFor = Exception.class)
public class SpittleRepositoryTest {

    @Autowired
    private SpittleRepository spittleRepository;

    @Test
    public void testGetSpittles() {
        List<Spittle> spittles = spittleRepository.getSpittles(2, 1);
        assertEquals(1, spittles.size());
        assertEquals("we aint going home", spittles.get(0).getMessage());
    }

    @Test
    public void testCount() {
        assertEquals(10, spittleRepository.count());
    }

    @Test
    public void testFindById() {
        assertEquals(1, spittleRepository.findById(1).getId());
    }

    @Test
    public void testFindBySpitterId() {
        List<Spittle> sl = spittleRepository.findBySpitterId(4);
        assertEquals(4, sl.size());
        assertEquals(8, sl.get(0).getId());
        assertEquals(7, sl.get(1).getId());
    }

    @Test
    public void testSave() {
        Spittle byId = spittleRepository.findById(10);
        Spittle spittle = new Spittle(byId.getSpitter(), "real dunk champ", new Date(), 0d, 0d);
        Spittle save = spittleRepository.save(spittle);
        assertEquals(11, spittleRepository.count());
        assertEquals(11, save.getId());
    }

    @Test
    public void testFindRecent() {
        List<Spittle> recent = spittleRepository.findRecent();
        assertEquals(10, recent.size());
        assertEquals(10, recent.get(9).getId());
        assertRecent(recent, 10);

        List<Spittle> r2 = spittleRepository.findRecent(5);
        assertEquals(5, r2.size());
        assertRecent(r2, 5);

    }

    private void assertRecent(List<Spittle> recent, int count) {
        int[] recentIds = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 10};
        assertEquals(count, recent.size());
        for (int i = 0; i < count; i++) {
            assertEquals(recentIds[i], recent.get(i).getId().longValue());
        }
    }

    @Test
    public void testDelete() {
        spittleRepository.delete(6);
        assertEquals(9, spittleRepository.count());
    }
}


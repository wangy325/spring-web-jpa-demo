package com.wangy.webmvc.data;

import com.wangy.webmvc.data.bean.Spittle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/26 / 00:19
 */
@SpringBootTest
@ActiveProfiles("dev0")
public class SpittleRepositoryTest {
    @Autowired
    private SpittleRepository spittleRepository;

    @Test
    @Transactional
    public void testCount() {
        assertEquals(10, spittleRepository.count());
    }

    @Test
    @Transactional
    public void testFindById() {
        assertEquals(1, spittleRepository.findById(1).getId());
    }

    @Test
    @Transactional
    public void testFindBySpitterId() {
        List<Spittle> sl = spittleRepository.findBySpitterId(4);
        assertEquals(4, sl.size());
        assertEquals(8, sl.get(0).getId());
        assertEquals(7, sl.get(1).getId());
    }

    @Test
    @Transactional
    public void testSave() {
        Spittle byId = spittleRepository.findById(10);
        Spittle spittle = new Spittle(byId.getSpitter(), "仁慈的父我已坠入", new Date(), 0d, 0d);
        Spittle save = spittleRepository.save(spittle);
        assertEquals(11, spittleRepository.count());
        assertEquals(11, save.getId());
    }

    @Test
    @Transactional
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
    @Transactional
    public void testDelete(){
        spittleRepository.delete(6);
        assertEquals(9, spittleRepository.count());
    }
}

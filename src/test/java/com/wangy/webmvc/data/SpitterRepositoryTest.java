package com.wangy.webmvc.data;

import com.wangy.webmvc.RepositoryTestConfig;
import com.wangy.webmvc.config.RootConfig;
import com.wangy.webmvc.data.bean.Spitter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * 测试的时候使用{@link ActiveProfiles}注解激活profile
 *
 * @author wangy
 * @version 1.0
 * @date 2020/3/17 / 16:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@ActiveProfiles("h2")
@Transactional
public class SpitterRepositoryTest {

    @Autowired
    private SpitterRepository spitterRepository;

    @Test
    public void testCount(){
        assertEquals(4, spitterRepository.count());
    }

    @Test
    public void findAll() {
        List<Spitter> spitters = spitterRepository.findAll();
        assertEquals(4, spitters.size());
        assertSpitter(0, spitters.get(0));
        assertSpitter(1, spitters.get(1));
        assertSpitter(2, spitters.get(2));
        assertSpitter(3, spitters.get(3));
    }

    @Test
    public void findByUsername() {
        assertSpitter(0, spitterRepository.findByUsername("sc30"));
        assertSpitter(1, spitterRepository.findByUsername("kt11"));
        assertSpitter(2, spitterRepository.findByUsername("kd35"));
        assertSpitter(3, spitterRepository.findByUsername("ai9"));
    }

    @Test
    public void findOne() {
        assertSpitter(0, spitterRepository.findOne(1));
        assertSpitter(1, spitterRepository.findOne(2));
        assertSpitter(2, spitterRepository.findOne(3));
        assertSpitter(3, spitterRepository.findOne(4));
    }

    @Test
    public void save_newSpitter() {
        assertEquals(4, spitterRepository.count());
        Spitter spitter = new Spitter(null, "draymond", "green", "dg23", "pass");
        Spitter saved = spitterRepository.save(spitter);
        assertEquals(5, spitterRepository.count());
        assertSpitter(4, saved);
        assertSpitter(4, spitterRepository.findOne(5));
    }

    @Test
    public void save_existingSpitter() {
        assertEquals(4, spitterRepository.count());
        Spitter spitter = new Spitter(4, "andrew", "wiggins", "aw22", "pass");
        Spitter saved = spitterRepository.save(spitter);
        assertSpitter(5, saved);
        assertEquals(4, spitterRepository.count());
        Spitter updated = spitterRepository.findOne(4);
        assertSpitter(5, updated);
    }

    private static void assertSpitter(int expectedSpitterIndex, Spitter actual) {
        Spitter expected = SPITTERS[expectedSpitterIndex];
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
    }

    private static Spitter[] SPITTERS = new Spitter[6];

    @Before
    public void before() {
        SPITTERS[0] = new Spitter(1, "stephen", "curry", "sc30", "pass");
        SPITTERS[1] = new Spitter(2, "klay", "thompson", "kt11", "pass");
        SPITTERS[2] = new Spitter(3, "kevin", "durant", "kd35", "pass");
        SPITTERS[3] = new Spitter(4, "andre", "iguodala", "ai9", "pass");
        SPITTERS[4] = new Spitter(5, "draymond", "green", "dg23", "pass");
        SPITTERS[5] = new Spitter(4, "andrew", "wiggins", "aw22", "pass");
    }
}

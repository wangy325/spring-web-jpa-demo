package com.wangy.webmvc.data;

import com.wangy.webmvc.data.bean.Spitter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
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
@SpringBootTest
@ActiveProfiles("dev0")
public class SpitterRepositoryTest {

    @Autowired
    private SpitterRepository spitterRepository;


    @Test
    @Transactional
    public void findAll() {
        List<Spitter> spitters = spitterRepository.findAll();
        assertEquals(4, spitters.size());
        assertSpitter(0, spitters.get(0));
        assertSpitter(1, spitters.get(1));
        assertSpitter(2, spitters.get(2));
        assertSpitter(3, spitters.get(3));
    }

    @Test
    @Transactional
    public void findByUsername() {
        assertSpitter(0, spitterRepository.findByUsername("skr"));
        assertSpitter(1, spitterRepository.findByUsername("ji"));
        assertSpitter(2, spitterRepository.findByUsername("daynight"));
        assertSpitter(3, spitterRepository.findByUsername("juhx"));
    }

    @Test
    @Transactional
    public void findOne() {
        assertSpitter(0, spitterRepository.findOne(1));
        assertSpitter(1, spitterRepository.findOne(2));
        assertSpitter(2, spitterRepository.findOne(3));
        assertSpitter(3, spitterRepository.findOne(4));
    }

    @Test
    @Transactional
    public void save_newSpitter() {
        assertEquals(4, spitterRepository.count());
        Spitter spitter = new Spitter(null, "jack", "chan", "jc", "pass");
        Spitter saved = spitterRepository.save(spitter);
        assertEquals(5, spitterRepository.count());
        assertSpitter(4, saved);
        assertSpitter(4, spitterRepository.findOne(5));
    }

    @Test
    @Transactional
    public void save_existingSpitter() {
        assertEquals(4, spitterRepository.count());
        Spitter spitter = new Spitter(4, "tanya", "cai", "rh", "pass");
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

    @BeforeAll
    static void before() {
        SPITTERS[0] = new Spitter(1, "cris", "wu", "skr", "pass");
        SPITTERS[1] = new Spitter(2, "cai", "xk", "ji", "pass");
        SPITTERS[2] = new Spitter(3, "jj", "lin", "daynight", "pass");
        SPITTERS[3] = new Spitter(4, "jay", "chou", "juhx", "pass");
        SPITTERS[4] = new Spitter(5, "jack", "chan", "jc", "pass");
        SPITTERS[5] = new Spitter(4, "tanya", "cai", "rh", "pass");
    }
}

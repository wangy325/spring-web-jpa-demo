package com.wangy.webmvc.data;

import com.wangy.webmvc.data.bean.Spitter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/17 / 16:07
 */
@SpringBootTest
@Slf4j
public class SpitterRepositoryTest {

    @Autowired
    private SpitterRepository  spitterRepository;

    @Test
    public void testSaveAndFind(){
        Spitter spitter =  new Spitter();
        spitter.setFirstName("wang");
        spitter.setLastName("yong");
        spitter.setUsername("wangy325");
        spitter.setPassword("123456");
        spitterRepository.save(spitter);
        Optional<Spitter> rs = spitterRepository.findById(1);
        Spitter spitter1 = rs.orElse(null);
        assert spitter1 != null;
        log.info("spitter1: {}", spitter1.getPassword());

    }


}

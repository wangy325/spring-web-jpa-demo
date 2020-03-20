package com.wangy.webmvc.data;

import com.wangy.webmvc.data.bean.Spitter;
import org.springframework.data.repository.CrudRepository;

import java.sql.SQLException;
import java.util.Optional;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/16 / 12:02
 */
public interface SpitterRepository {

    Spitter register(Spitter spitter);

    Spitter findByUsername(String username);

}

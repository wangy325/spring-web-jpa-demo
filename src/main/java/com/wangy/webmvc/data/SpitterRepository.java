package com.wangy.webmvc.data;

import com.wangy.webmvc.data.bean.Spitter;
import org.springframework.data.repository.CrudRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/16 / 12:02
 */
public interface SpitterRepository {

    /**
     * insert a row into spitter, or update exists spitter
     * @param spitter {@link Spitter}
     * @return the spitter inserted into db with primary key
     */
    Spitter save(Spitter spitter);

    /**
     * find a spitter by username
     * @param username {@link Spitter} username
     * @return the matchable spitter
     */
    Spitter findByUsername(String username);

    /**
     * count number of spitter
     * @return the count
     */
    int count();

    /**
     * find a spitter by id
     * @param id spitter id
     * @return spitter with specified id
     */
    Spitter findOne(int id);

    /**
     * find all spitters
     * @return all spitter list
     */
    List<Spitter> findAll();

}

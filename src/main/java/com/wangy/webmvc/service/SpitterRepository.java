package com.wangy.webmvc.service;

import com.wangy.webmvc.entity.Spitter;

import java.util.List;

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

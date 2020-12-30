package com.wangy.webmvc.service;

import com.wangy.webmvc.entity.Spittle;

import java.util.List;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/14 / 20:30
 */
public interface SpittleRepository {

    /**
     * fetch assigned spittles which id less than given parameter {@code max}，
     *
     * @param max   the max spittle id
     * @param count spittle count expected
     * @return a list of spittle
     */
    List<Spittle> getSpittles(long max, int count);

    /**
     * fetch spittle by id
     *
     * @param id spittle id
     * @return spittle  with particular id
     */
    Spittle findById(long id);

    /**
     * fetch particular spitter's all  spittles
     *
     * @param spitterId spitter's id
     * @return a list of spittles
     */
    List<Spittle> findBySpitterId(int spitterId);

    /**
     * count all spittle's number
     *
     * @return number of spittles
     */
    long count();

    /**
     * find recent 10 spittles
     *
     * @return a list of recent 10 spittles
     */
    List<Spittle> findRecent();

    /**
     * find recent {@code count} spittles
     *
     * @param count the spittle number expected
     * @return a list of expect count of spittles
     */
    List<Spittle> findRecent(int count);

    /**
     * insert/update spittle
     *
     * @param spittle spittle to insert or update to db
     * @return spittle
     */
    Spittle save(Spittle spittle);

    /**
     * delete a spittle by id
     *
     * @param id the spittle id to delete
     */
    void delete(long id);
}

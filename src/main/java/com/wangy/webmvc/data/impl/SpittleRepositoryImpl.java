package com.wangy.webmvc.data.impl;

import com.wangy.webmvc.data.SpittleRepository;
import com.wangy.webmvc.data.bean.Spittle;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/14 / 21:27
 */
@Repository
public class SpittleRepositoryImpl implements SpittleRepository {
    @Override
    public List<Spittle> getSpittles(long max, int count) {
        List<Spittle> spittleList = new ArrayList<>();
        spittleList.add(new Spittle("Hello, my first spittle.", DateUtils.addHours(new Date(), -6)));
        spittleList.add(new Spittle("Hello,  spittle again!", DateUtils.addHours(new Date(), -5)));
        spittleList.add(new Spittle("bad app!", DateUtils.addHours(new Date(), -4)));
        spittleList.add(new Spittle("dont know how to write code.", DateUtils.addHours(new Date(), -3)));
        spittleList.add(new Spittle("spring's  fucking awesome", DateUtils.addHours(new Date(), -2)));
        spittleList.add(new Spittle("开冲！", new Date()));
        return spittleList;
    }

    @Override
    public Spittle findOne(long id) {
        return null;
    }
}

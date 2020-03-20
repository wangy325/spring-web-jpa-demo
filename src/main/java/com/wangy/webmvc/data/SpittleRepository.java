package com.wangy.webmvc.data;

import com.wangy.webmvc.data.bean.Spittle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/14 / 20:30
 */
public interface SpittleRepository {

    List<Spittle> getSpittles(long max , int count);

    Spittle findOne(long id);
}

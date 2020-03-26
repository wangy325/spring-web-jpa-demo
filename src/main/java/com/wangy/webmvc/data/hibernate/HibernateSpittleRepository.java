package com.wangy.webmvc.data.hibernate;

import com.wangy.webmvc.data.SpittleRepository;
import com.wangy.webmvc.data.bean.Spittle;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/26 / 22:52
 */
@Repository
@Qualifier("hibernateSpittleRepo")
public class HibernateSpittleRepository implements SpittleRepository {

    private SessionFactory sessionFactory;

    public HibernateSpittleRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    private Criteria spittleCriteria() {
        return currentSession().createCriteria(Spittle.class).addOrder(Order.desc("time"));
    }

    @Override
    public List<Spittle> getSpittles(long max, int count) {
        return null;
    }

    @Override
    public Spittle findById(long id) {
        return currentSession().get(Spittle.class, id);
    }

    @Override
    public List<Spittle> findBySpitterId(int spitterId) {
        return (List<Spittle>) spittleCriteria()
            .add(Restrictions.eq("spitter.id", spitterId))
            .list();
    }

    @Override
    public long count() {
        return spittleCriteria().list().size();
    }

    @Override
    public List<Spittle> findRecent() {
        return findRecent(10);
    }

    @Override
    public List<Spittle> findRecent(int count) {
        return (List<Spittle>) spittleCriteria()
            .setMaxResults(count)
            .list();
    }

    @Override
    public Spittle save(Spittle spittle) {
        Serializable id = currentSession().save(spittle);
        return new Spittle(
            (Long) id,
            spittle.getSpitter(),
            spittle.getMessage(),
            spittle.getTime(),
            spittle.getLatitude(),
            spittle.getLongitude());
    }

    @Override
    public void delete(long id) {
        currentSession().delete(findById(id));
    }
}

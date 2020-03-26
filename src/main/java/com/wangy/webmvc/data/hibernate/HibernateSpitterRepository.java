package com.wangy.webmvc.data.hibernate;

import com.wangy.webmvc.data.SpitterRepository;
import com.wangy.webmvc.data.bean.Spitter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/26 / 19:26
 */
@Repository
@Qualifier("hibernateSpitterRepo")
public class HibernateSpitterRepository implements SpitterRepository {

    private SessionFactory sessionFactory;

    public HibernateSpitterRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Spitter save(Spitter spitter) {
        Serializable id = currentSession().save(spitter);
        return new Spitter((int) id,
            spitter.getUsername(),
            spitter.getPassword(),
            spitter.getFirstName(),
            spitter.getLastName());

    }

    @Override
    public Spitter findByUsername(String username) {
        return (Spitter) currentSession()
            .createCriteria(Spitter.class)
            .add(Restrictions.eq("username", username))
            .list().get(0);
    }

    @Override
    public int count() {
        return findAll().size();
    }

    @Override
    public Spitter findOne(int id) {
        return currentSession().get(Spitter.class, id);
    }

    @Override
    public List<Spitter> findAll() {
        return (List<Spitter>) currentSession().createCriteria(Spitter.class).list();
//        currentSession().getCriteriaBuilder().createQuery().
    }
}

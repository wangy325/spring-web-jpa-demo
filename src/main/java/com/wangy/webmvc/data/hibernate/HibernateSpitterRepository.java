package com.wangy.webmvc.data.hibernate;

import com.wangy.webmvc.data.SpitterRepository;
import com.wangy.webmvc.data.bean.Spitter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

        /*currentSession()
            .createQuery("INSERT INTO Spitter (firstName,lastName,username,password)" +
                " SELECT (firstName, lastName,username,password) FROM Spitter ");*/

        // TODO 无法获取主键
        /*int i = currentSession().createNativeQuery("INSERT INTO spitter (firstName, lastName, username, password) " +
            "values (:1st, :2nd, :3rd, :4th)")
            .setParameter("1st", spitter.getFirstName())
            .setParameter("2nd", spitter.getLastName())
            .setParameter("3rd", spitter.getUsername())
            .setParameter("4th", spitter.getUsername())
            .executeUpdate();*/
    }

    /**
     * 列出3种方法使用参数绑定查询
     *
     * @param username {@link Spitter} username
     * @return
     */
    @Override
    public Spitter findByUsername(String username) {
        // hibernate的currentSession().createCriteria(Spitter.class)方法已过期
        // hibernate建议使用JPA CriteriaQuery实现参数查询
        /*CriteriaBuilder cb = currentSession().getCriteriaBuilder();
        CriteriaQuery<Spitter> criteriaQuery = cb.createQuery(Spitter.class);
        Root<Spitter> spitterRoot = criteriaQuery.from(Spitter.class);
        criteriaQuery.select(spitterRoot).where(cb.equal(spitterRoot.get("username"),username));
        Query<Spitter> query = currentSession().createQuery(criteriaQuery);
        return query.getSingleResult();*/

/* **************************************************************************/
        // 或者使用hibernate所支持的原生SQL进行带参查询
        // addEntity()搭配createNativeQuery(String sqlString)
        // createNativeQuery(String  sqlString, Class <R> resultClass)可单独使用
        // 原因查看api
        return currentSession()
            .createNativeQuery("SELECT * FROM spitter WHERE spitter.username = :para",Spitter.class)
//            .addEntity(Spitter.class)
            .setParameter("para", username)
            .getSingleResult();

/* **************************************************************************/
        // or use The HQL queryString
        // HQL一行抵2行
        /*return currentSession()
            .createQuery("SELECT s FROM Spitter s WHERE s.username = :para", Spitter.class)
            .setParameter("para", username)
            .getSingleResult();*/

    }

    @Override
    public int count() {
//        return findAll().size();

        // use criteriaQuery
        /*CriteriaBuilder cb = currentSession().getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
        Root<Spitter> spitterRoot = criteriaQuery.from(Spitter.class);
        criteriaQuery.select(cb.count(spitterRoot));
        Query<Long> query = currentSession().createQuery(criteriaQuery);
        return query.getSingleResult().intValue();*/

        // use HQL
        /*return currentSession()
            .createQuery("SELECT count(s) FROM Spitter s ", Long.class)
            .getSingleResult().intValue();*/

        // use native SQL
        Number result = (Number) currentSession()
            .createNativeQuery("SELECT COUNT(*) FROM spitter")
            .getSingleResult();
        return result.intValue();
    }

    @Override
    public Spitter findOne(int id) {
        // use session.get()
//        return currentSession().get(Spitter.class, id);

        // use HQL
        return currentSession()
            .createQuery("SELECT s FROM Spitter s WHERE s.id = :id", Spitter.class)
            .setParameter("id",id)
            .getSingleResult();
    }

    @Override
    public List<Spitter> findAll() {
        // usr criteriaQuery
       /* CriteriaBuilder cb = currentSession().getCriteriaBuilder();
        CriteriaQuery<Spitter> criteriaQuery = cb.createQuery(Spitter.class);
        Root<Spitter> spitterRoot = criteriaQuery.from(Spitter.class);
        criteriaQuery.select(spitterRoot);
        Query<Spitter> query = currentSession().createQuery(criteriaQuery);
        return query.getResultList();*/

       // usr HQL
        /*return currentSession()
            .createQuery("SELECT s FROM Spitter s", Spitter.class)
            .getResultList();*/

        // use native SQL
        return currentSession()
            .createNativeQuery("SELECT * FROM spitter", Spitter.class)
            .getResultList();
    }
}

package com.wangy.webmvc.data.hibernate;

import com.wangy.webmvc.data.SpittleRepository;
import com.wangy.webmvc.data.bean.Spittle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/26 / 22:52
 */
@Repository
@Qualifier("hibernateSpittleRepo")
@SuppressWarnings("all")
public class HibernateSpittleRepository implements SpittleRepository {

    private SessionFactory sessionFactory;

    public HibernateSpittleRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Spittle> getSpittles(long max, int count) {
        // use HQL
        /*List<Spittle> resultList = currentSession()
            .createQuery("SELECT s FROM Spittle s WHERE s.id < :max ORDER BY s.time DESC ", Spittle.class)
            .setMaxResults(count)
            .setParameter("max", max)
            .getResultList();*/

        // use Criteria Query
        CriteriaBuilder cb = currentSession().getCriteriaBuilder();
        CriteriaQuery<Spittle> cq = cb.createQuery(Spittle.class);
        Root<Spittle> root = cq.from(Spittle.class);
        cq.select(root)
            .where(cb.lessThan(root.get("id"),max))
            .orderBy(cb.desc(root.get("time")));
        Query<Spittle> query = currentSession().createQuery(cq);
        List<Spittle> resultList = query.setMaxResults(count).getResultList();

        return resultList;
    }

    @Override
    public Spittle findById(long id) {
        return currentSession().get(Spittle.class, id);
    }

    @Override
    public List<Spittle> findBySpitterId(int spitterId) {
        /************** 以下3种criteriaQuery方法记录了其简化过程 ************/
        CriteriaBuilder cb = currentSession().getCriteriaBuilder();

        // 1. use criteriaQuery from and join multiSelect
        /*CriteriaQuery<Object[]> spittleQuery = cb.createQuery(Object[].class);
        Root<Spittle> spittleRoot = spittleQuery.from(Spittle.class);
        Root<Spitter> spitterRoot = spittleQuery.from(Spitter.class);
        spittleQuery
            .multiselect(spittleRoot,spitterRoot)
            .where(cb.equal(spittleRoot.get("spitter"), spitterRoot.get("id"))
                , cb.equal(spitterRoot.get("id"), spitterId))
            .orderBy(cb.desc(spittleRoot.get("time")));
        ;
        Query<Object[]> query = currentSession().createQuery(spittleQuery);
        List<Object[]> list = query.getResultList();
        List<Spittle> resultList = new ArrayList<>();
        for (Object[] objects : list) {
            resultList.add((Spittle) (objects[0]));
        }*/


        // 2. use criteriaQuery from and join
        // 上一个方法中获取spittle时，发现spittle对象已经拼装好了，于是便没有必要multiSelect了
        /*CriteriaQuery<Spittle> spittleQuery = cb.createQuery(Spittle.class);
        Root<Spittle> spittleRoot = spittleQuery.from(Spittle.class);
        Root<Spitter> spitterRoot = spittleQuery.from(Spitter.class);
        spittleQuery
            .select(spittleRoot)
            .where(cb.equal(spittleRoot.get("spitter"), spitterRoot.get("id"))
                , cb.equal(spitterRoot.get("id"), spitterId))
            .orderBy(cb.desc(spittleRoot.get("time")));
        Query<Spittle> query = currentSession().createQuery(spittleQuery);
        List<Spittle> resultList = query.getResultList();*/


        // 3. use criteriaQuery where
        // 既然可以只用select，那么方法3显然时更简洁的
        // TODO: 为什么hibernate会查2次去获取Spittle.spitter的信息,并且自动拼装对象
        /*CriteriaQuery<Spittle> cq = cb.createQuery(Spittle.class);
        Root<Spittle> spittleRoot = cq.from(Spittle.class);
        cq.select(spittleRoot)
            .where(cb.equal(spittleRoot.get("spitter").get("id"), spitterId))
            .orderBy(cb.desc(spittleRoot.get("time")));
        Query<Spittle> query = currentSession().createQuery(cq);
        List<Spittle> resultList = query.getResultList();*/
/*************************************************************************************/

        // usr HQL 等效于方法2
        /*List<Spittle> resultList = currentSession()
            .createQuery("SELECT t1 FROM Spittle t1 , Spitter t2 WHERE t1.spitter.id = t2.id AND t2.id = :spitterId ORDER BY t1.time DESC", Spittle.class)
            .setParameter("spitterId", spitterId)
            .getResultList();*/

        // use native SQL
        // 你还真行啊
        List<Spittle> resultList = currentSession()
            .createNativeQuery("SELECT t1.* FROM spittle t1 , spitter t2 WHERE t1.spitterId = t2.id AND t2.id = :spitterId ORDER BY t1.time DESC", Spittle.class)
            .setParameter("spitterId", spitterId)
            .getResultList();

        return resultList;
    }

    @Override
    public long count() {
        return currentSession()
            .createQuery("SELECT COUNT(s) FROM Spittle s ", Long.class)
            .getSingleResult();
    }

    @Override
    public List<Spittle> findRecent() {
        return findRecent(10);
    }

    @Override
    public List<Spittle> findRecent(int count) {
        // use HQL
        /*List<Spittle> resultList = currentSession()
            .createQuery("SELECT s FROM Spittle s ORDER BY s.time DESC ", Spittle.class)
            .setMaxResults(count)
            .getResultList();*/

        // use Criteria Query
        CriteriaBuilder cb = currentSession().getCriteriaBuilder();
        CriteriaQuery<Spittle> cq = cb.createQuery(Spittle.class);
        Root<Spittle> root = cq.from(Spittle.class);
        cq.select(root).orderBy(cb.desc(root.get("time")));
        Query<Spittle> query = currentSession().createQuery(cq);
        List<Spittle> resultList = query.setMaxResults(count).getResultList();

        return resultList;
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

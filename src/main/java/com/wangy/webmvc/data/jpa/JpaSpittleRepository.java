package com.wangy.webmvc.data.jpa;

import com.wangy.webmvc.config.condition.PersistenceType;
import com.wangy.webmvc.data.SpittleRepository;
import com.wangy.webmvc.data.bean.Spittle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;



/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/29 / 19:02
 */
@Repository
@Qualifier("jpaSpittleRepository")
@PersistenceType("jpa")
@Slf4j
@SuppressWarnings("all")
public class JpaSpittleRepository implements SpittleRepository {

    /**
     * 以下注入方式二选一
     */
    @PersistenceContext
    private EntityManager em;

   /*private EntityManager em;

   public JpaSpittleRepository(EntityManager emf) {
        this.em = emf;
    }*/

    @Override
    public List<Spittle> getSpittles(long max, int count) {
        // use JPQL
        // 其实和HQL一样
        List<Spittle> resultList = em
            .createQuery("SELECT s FROM Spittle s WHERE s.id < :max ORDER BY s.time DESC", Spittle.class)
            .setMaxResults(count)
            .setParameter("max", max)
            .getResultList();

        // use Criteria Query
        /*CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Spittle> cq = cb.createQuery(Spittle.class);
        Root<Spittle> root = cq.from(Spittle.class);
        cq.select(root)
            .where(cb.lessThan(root.get("id"), max))
            .orderBy(cb.desc(root.get("time")));
        List<Spittle> resultList = em.createQuery(cq)
            .setMaxResults(count)
            .getResultList();*/

        // use native SQL
        // 代码省略...
        return resultList;
    }

    @Override
    public Spittle findById(long id) {
        return em.find(Spittle.class, id);
    }

    @Override
    public List<Spittle> findBySpitterId(int spitterId) {
        return em
            .createQuery("SELECT s FROM Spittle s, Spitter ss WHERE s.spitter.id = ss.id AND ss.id = :id ORDER BY s.time DESC", Spittle.class)
            .setParameter("id", spitterId)
            .getResultList();
    }

    @Override
    public long count() {
        return em
            .createQuery("SELECT COUNT(s) FROM Spittle s", Long.class)
            .getSingleResult();
    }

    @Override
    public List<Spittle> findRecent() {
        return findRecent(10);
    }

    @Override
    public List<Spittle> findRecent(int count) {
        return em
            .createQuery("SELECT s FROM Spittle s ORDER BY s.time DESC ", Spittle.class)
            .setMaxResults(count)
            .getResultList();
    }

    @Override
    public Spittle save(Spittle spittle) {
        em.persist(spittle);
        // persist成功之后，spittle对象的主键会更新
        log.debug("generate id: {}", spittle.getId());
        return spittle;
    }

    @Override
    public void delete(long id) {
        em.remove(findById(id));
    }
}

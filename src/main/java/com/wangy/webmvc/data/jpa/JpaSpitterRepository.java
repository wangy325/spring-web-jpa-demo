package com.wangy.webmvc.data.jpa;

import com.wangy.webmvc.config.condition.PersistenceType;
import com.wangy.webmvc.data.SpitterRepository;
import com.wangy.webmvc.data.bean.Spitter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/29 / 19:01
 */
@Repository
@Qualifier("jpaSpitterRepository")
@PersistenceType("jpa")
@Slf4j
public class JpaSpitterRepository implements SpitterRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Spitter save(Spitter spitter) {
        if (null == spitter.getId()) {
            em.persist(spitter);
            // TODO 无法获取主键
           /*em.createNativeQuery("INSERT INTO spitter (firstName, lastName, username, password) VALUES (:_1, :_2,:_3,:_4)")
                .setParameter("_1", spitter.getFirstName())
                .setParameter("_2", spitter.getLastName())
                .setParameter("_3", spitter.getUsername())
                .setParameter("_4", spitter.getPassword())
                .executeUpdate();
            em.refresh(spitter);*/
            log.debug("the generated id: {}", spitter.getId());
        } else {
            // update
            int i = em
                .createQuery("UPDATE spitter s SET s.firstName = :_1, s.lastName = :_2, s.username = :_3, s.password = :_4")
                .setParameter("_1", spitter.getFirstName())
                .setParameter("_2", spitter.getLastName())
                .setParameter("_3", spitter.getUsername())
                .setParameter("_4", spitter.getPassword())
                .executeUpdate();
        }
        return spitter;
    }

    @Override
    public Spitter findByUsername(String username) {
        // use Criteria Query
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Spitter> cq = cb.createQuery(Spitter.class);
        Root<Spitter> root = cq.from(Spitter.class);
        cq.select(root).where(cb.equal(root.get("username"), username));
        return em.createQuery(cq).getSingleResult();
    }

    @Override
    public int count() {
        return em
            .createQuery("SELECT COUNT(s) FROM spitter s", Long.class)
            .getSingleResult()
            .intValue();
    }

    @Override
    public Spitter findOne(int id) {
        return em.find(Spitter.class, id);
    }

    @Override
    public List<Spitter> findAll() {
        return em.createQuery("SELECT s FROM spitter s ", Spitter.class).getResultList();
    }
}

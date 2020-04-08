package com.wangy.webmvc.data.bean;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * TODO: 注意{@link ManyToOne}和{@link JoinColumn}注解
 *
 * <pre>
 *
 *   &#064;ManyToOne(fetch = FetchType.LAZY)
 *
 *   意味着Session/entityManager不自动级连获取持久化字段信息，此情况下，会获取一个含有拦截器的{@link Spitter}
 *   对象，当程序想获取{@link Spitter}的详细信息时，Session/entityManager会再从数据库中获取，此属性默认值为
 *   <code>EAGER</code>，参照<a href="Retrieval by Eager Fetch">
 *       https://www.objectdb.com/java/jpa/persistence/retrieve#Retrieval_by_Eager_Fetch </a>
 *
 * </pre>
 *
 * @author wangy
 * @version 1.0
 * @date 2020/3/14 / 20:33
 * @see Spitter
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"id", "time"})
@Entity
public class Spittle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spitterId")
    private Spitter spitter;
    private String message;
    private Date time;
    private Double latitude;
    private Double longitude;

    public Spittle() {
        // this constructor is for hibernate
    }

    public Spittle(String message, Date time) {
        this(null, message, time, null, null);
    }

    public Spittle(Long id, String message, Date time) {
        this(id, null, message, time, null, null);
    }

    public Spittle(Spitter spitter, String message, Date time, Double latitude, Double longitude) {
        this.id = null;
        this.spitter = spitter;
        this.message = message;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Spittle(Long id, Spitter spitter, String message, Date time, Double latitude, Double longitude) {
        this.id = id;
        this.spitter = spitter;
        this.message = message;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /*
     * 使用Apache Common Lang实现hashcode和equals方法
     *
     * @param that
     * @return
     */
    /*@Override
    public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that, "id", "time");
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, "id", "time");
    }*/
}

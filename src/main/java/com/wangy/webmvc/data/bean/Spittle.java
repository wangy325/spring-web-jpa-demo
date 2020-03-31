package com.wangy.webmvc.data.bean;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 注意{@link ManyToOne}和{@link JoinColumn}注解
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
    /** TODO 搞清楚这两个注解(@ManyToOne,@JoinColumn)的意思 */
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

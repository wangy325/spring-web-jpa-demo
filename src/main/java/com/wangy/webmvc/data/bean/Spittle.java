package com.wangy.webmvc.data.bean;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/14 / 20:33
 */
@Getter
@EqualsAndHashCode(exclude = {"id", "time"})
@Entity
public class Spittle {
    @Id
    @GeneratedValue
    private Long id;
    private String message;
    private Date time;
    private Double latitude;
    private Double longitude;

    public Spittle() {
    }

    public Spittle(String message, Date time) {
        this(message, time, null, null);
    }

    public Spittle(Long id, String message, Date time) {
        this(id, message, time, null, null);
    }

    public Spittle(String message, Date time, Double latitude, Double longitude) {
        this.id = null;
        this.message = message;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Spittle(Long id, String message, Date time, Double latitude, Double longitude) {
        this.id = id;
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

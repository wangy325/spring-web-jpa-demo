package com.wangy.webmvc.data.bean;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/3/14 / 20:33
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"id", "time"})
public class Spittle {

    private Long id;
    private Spitter spitter;
    private String message;
    private Date time;
    private Double latitude;
    private Double longitude;

    public Spittle() {
    }

    public Spittle(String message, Date time) {
        this(null, message, time, null, null);
    }

    public Spittle(Long id, String message, Date time) {
        this(id, null,  message, time, null, null);
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

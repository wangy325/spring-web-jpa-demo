package com.wangy.webmvc.data.bean;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 使用javax.persistence包下的&#064;{@link Entity}注解指定java bean和database table之间的映射<br>
 * &#064;{@link Entity},&#064;{@link Id}, &#064;{@link Column}, &#064;{@link GeneratedValue}
 * 注解的效果等同于<em>spitter.hbm.xml</em>文件。
 * 此配置文件（如果存在）用来定义<b>hibernate</b>的元数据（mapper映射关系）：
 *
 * <pre class="code">
 * &lt;?xml version="1.0"?&gt;
 * &lt;!DOCTYPE hibernate-mapping PUBLIC
 *              "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
 *              "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd"&gt;
 *
 * &lt;hibernate-mapping package="com.wangy.webmvc.data.bean"&gt;
 *      &lt;class name="Spitter" table="spitter"&gt;
 *          &lt;id name="id" column="id"&gt;
 *              &lt;generator class="increment"/&gt;
 *          &lt;/id&gt;
 *      &lt;property name="firstName" column="firstname"/&gt;
 *      &lt;property name="lastName" /&gt;
 *      &lt;property name="username" /&gt;
 *      &lt;property name="password" /&gt;
 *      &lt;/class&gt;
 * &lt;/hibernate-mapping&gt;
 * </pre>
 * <p>
 * &#064;{@link NotNull}和&#064;{@link Size}注解位于<code>javax.validation.constraints</code>包下，用于简单的参数校验
 *
 * @author wangy
 * @version 1.0
 * @date 2020/3/16 / 12:03
 */

@Setter
@Getter
@EqualsAndHashCode
@ToString
@Entity
public class Spitter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Size(min = 2, max = 20)
    @Column(name = "firstName")
    private String firstName;
    @NotNull
    @Size(min = 2, max = 20)
    @Column(name = "lastName")
    private String lastName;
    @NotNull
    @Size(min = 2, max = 16)
    @Column(name = "username")
    private String username;
    @NotNull
    @Size(min = 4, max = 30)
    @Column(name = "password")
    private String password;

    /**使用&#064;{@link OneToMany}处理一对多映射关系*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "spitter")
    private Collection<Spittle> spittles = new ArrayList<>();


    public Spitter() {
        // this constructor is for hibernate
    }

    public Spitter(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public Spitter(Integer id, String firstName, String lastName, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }
}

package com.wangy.webmvc.config.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * by using <code>&#64;PersistenceType(value="value")</code> on a spring configuration class,
 * the spring ApplicationContext know whether or not to instantiate this bean.
 * <p>
 * the jdbc sessionFactory/jdbcRepository will be instantiated by default.
 *
 * @author wangy325
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(PersistenceTypeCondition.class)
public @interface PersistenceType {

    String value();
}

package com.wangy.webmvc.config.condition;


import org.springframework.context.annotation.Conditional;
import org.springframework.orm.jpa.JpaVendorAdapter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * use this condition to configure {@link JpaVendorAdapter}'s database type and other info.
 *
 * @author wangy325
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Conditional(JpaDatabaseTypeCondition.class)
public @interface JpaDatabaseType {

    String value() default "h2";
}

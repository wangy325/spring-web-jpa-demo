package com.wangy.webmvc.config.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Objects;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/12/13 / 00:24
 */
public class JpaDatabaseTypeCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        String value = (String) Objects.requireNonNull(metadata.getAnnotationAttributes(JpaDatabaseType.class.getName()))
            .get("value");

        String[] profiles = context.getEnvironment().getActiveProfiles();
        StringBuilder profile = new StringBuilder();
        for (String s : profiles) {
            profile.append(s);
        }
        return profile.toString().contains(value);
    }
}

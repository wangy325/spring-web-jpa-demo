package com.wangy.webmvc.config.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/12/12 / 01:06
 */
public class PersistenceTypeCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> attrs = metadata.getAnnotationAttributes(PersistenceType.class.getName());
        String persistenceType = null;
        if (attrs != null && !attrs.isEmpty()) {
            persistenceType = (String) attrs.get("value");
        }
        String property = System.getProperty("persistenceType","jdbc");
        return persistenceType != null && persistenceType.equals(property);
    }
}

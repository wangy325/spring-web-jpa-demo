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
        // 1. 获取系统参数；一般使用 java -jar -persistenceType=jpa 来配置
//        String property = System.getProperty("persistenceType");

        // 2. 获取spring环境配置，可以读取系统参数和上下文参数，以及配置文件参数
        String property = context.getEnvironment().getProperty("spring.persistenceType");
        return persistenceType != null && persistenceType.equals(property);
    }
}

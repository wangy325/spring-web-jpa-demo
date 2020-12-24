package com.wangy.webmvc.config.condition;

import com.wangy.webmvc.config.properties.PropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author wangy
 * @version 1.0
 * @date 2020/12/12 / 01:06
 */
@Component
public class PersistenceTypeCondition implements Condition {
    @Autowired
    private PropertiesConfig propertiesConfig;

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
//        String property = context.getEnvironment().getProperty("spring.persistenceType");
        // 使用yml配置时，不能使用上述key值
        // TODO: 应该不是完美的解决方案
        String property = context.getEnvironment().getProperty("persistenceType");
        return persistenceType != null && persistenceType.equals(property);
    }
}

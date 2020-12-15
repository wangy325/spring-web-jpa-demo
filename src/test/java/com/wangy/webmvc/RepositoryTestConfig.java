package com.wangy.webmvc;

@Deprecated
public abstract class RepositoryTestConfig {

    // this construction block set program argument "persistenceType",
    // which values in [jdbc, hibernate, jpa], to decide which persistence repository to use.
    // the idea configurations can not set program argument when running a junit test.
    // once this property was set, spring will know which (datasource)sessionFactory/repository
    // to use by @conditional injection.

    {
        /**
         * use context properties to set persistenceType,
         * this method useless now.
         */
        System.setProperty("persistenceType", "jpa");
    }

}

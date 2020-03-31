# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/maven-plugin/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#using-boot-devtools)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
 

---

# Spring In Action 

## spring web and spring jdbc 代码记录

- 基于java bean配置的spring web项目
- jsp （java server page）
- 使用MockMvc测试控制器
- `javax.validation`进行bean字段简单校验
- 使用H2内存数据库和Spring JDBC Template实现持久化
- Spring Boot自动配置Hakari数据库连接池（多数据源时需手动配置）
- 配置多数据源并激活所需配置(多种激活方式，引入mysql数据库)
- 使用hibernate实现持久化（基于注解和java配置类）
- hibernate query的几种方式
- jpa配置的代码在git:jpa分支下
- hibernate的`SessionFactory`继承了JPA的`EntityManagerFactory`类，因此在一个项目里同时激活这两个配置会出现冲突
- `javax.persitence.*`下的几个注解`@Entity`，`@Id`，`@GeneratedValue`，`@ManytoOne`，`@JoinColumn`，`@OneToMany`，`@MappedBy`...
spring data jpa

关于Spring data jpa的一些知识点：

SpringData : Spring 的一个子项目。用于简化数据库访问，支持NoSQL 和 关系数据存储。其主要目标是使数据库的访问变得方便快捷。

SpringData 项目所支持 NoSQL 存储：

> -  MongoDB （文档数据库）
> -  Neo4j（图形数据库）
> -  Redis（键/值存储）
> -  Hbase（列族数据库）

SpringData 项目所支持的关系数据存储技术：

> - JDBC
> - JPA

JPA Spring Data ： 致力于减少数据访问层 (DAO) 的开发量， 开发者唯一要做的就只是声明持久层的接口，其他都交给 Spring Data JPA 来帮你完成！

**Spring Data JPA 进行持久层(即Dao)开发一般分三个步骤：**

- - 声明持久层的接口，该接口继承 Repository（或Repository的子接口，其中定义了一些常用的增删改查，以及分页相关的方法）。
  - 在接口中声明需要的业务方法。Spring Data 将根据给定的策略生成实现代码。
  - 在 Spring 配置文件中增加一行声明，让 Spring 为声明的接口创建代理对象。配置了 <jpa:repositories> 后，Spring 初始化容器时将会扫描 base-package 指定的包目录及其子目录，为继承 Repository 或其子接口的接口创建代理对象，并将代理对象注册为 Spring Bean，业务层便可以通过 Spring 自动封装的特性来直接使用该对象。

换句话来说，我们只需要在dao层声明接口，接口中定义我们要的方法，且接口继承Repository接口或者是Repository的子接口，这样就可以操作数据库了。但是在接口中定义的方法是要符合一定的规则的。

```
1.Repository是一个空接口，即是一个标记接口，
2.若我们定义的接口继承了Repository，则该接口会被IOC容器识别为一个Repository Bean 注入到IOC容器中，进而可以在该接口中定义满足一定规则的接口，
3.实际上也可以通过一个注解@RepositoryDefination 注解来替代Repository接口，比如：

//他们两个的作用是一样的，如果不写继承类就在接口上写@RepositoryDefinition注解
@RepositoryDefinition(domainClass=Person.class,idClass=Integer.class)
public interface PersonDao <!-- extends Repository<Person, Integer> --> {
    // 通过id查找实体
    Person getById(Integer id);
}
```

1：Repository：最顶层的接口，是一个空的接口，目的是为了统一所有Repository的类型，且能让组件扫描的时候自动识别。

2：CrudRepository ：是Repository的子接口，提供CRUD的功能

3：PagingAndSortingRepository：是CrudRepository的子接口，添加分页和排序的功能

4：JpaRepository：是PagingAndSortingRepository的子接口，增加了一些实用的功能，比如：批量操作等。

5：JpaSpecificationExecutor：用来做负责查询的接口

6：Specification：是Spring Data JPA提供的一个查询规范，要做复杂的查询，只需围绕这个规范来设置查询条件即可
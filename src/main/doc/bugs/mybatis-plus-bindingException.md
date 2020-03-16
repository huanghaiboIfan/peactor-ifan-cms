##  Mybatis-plus BindingException

该异常为初次创建项目测试时候遇见的异常，在确认了SpringBoot扫描到了Mapper接口类并且Mapper接口与Mapper.xml一一对应的情况下，依然报错，此时，有可能是因为依赖的冲突所导致的。

我们在springboot项目中，不应选用传统依赖，而是应该选用springboot相关依赖，具体如下：

```
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.3.1.tmp</version>
</dependency>
```

如此便可以解决BindingException问题。
# Mybatis Plus

Mybatis Plus框架是在Mybatis框架基础上扩展而来的框架

它包含Mybatis框架原有的所有功能,使用方式和Mybatis一致

只是在原有功能的基础上又添加了一些实用的功能更,供我们操作数据库使用

## 配置MybatisPlus

我们现在继承的SpringBoot2.3.12.RELEASE中内置了很多常用框架的版本号

可以看到,现在pom.xml文件中添加的依赖,是没有指定版本号的

MybatisPlus框架也需要添加pom.xml文件的依赖才能使用,但是SpringBoot框架中没有内置它的版本号,所以我们需要自己定义版本号

为了避免每个子项目都编写版本号造成维护困难,我们可以在父项目中定义MybatisPlus框架的版本号,供子项目使用,子项目就减少冗余了!

转到父项目的pom.xml文件修改如下

```xml
<properties>
    <java.version>1.8</java.version>
    <mybatis.plus.version>3.3.1</mybatis.plus.version>
</properties>
<packaging>pom</packaging>
<modules>
    <module>knows-portal</module>
</modules>
<dependencyManagement>
    <dependencies>
        <!--  下面定义了子项目在添加对应依赖时的版本号  -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-extension</artifactId>
            <version>${mybatis.plus.version}</version>
        </dependency>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>${mybatis.plus.version}</version>
        </dependency>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-generator</artifactId>
            <version>${mybatis.plus.version}</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```

子项目pom.xml文件添加依赖

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
</dependency>
```

需要连接数据库还要配置application.properties

```properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/knows?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true
spring.datasource.username=root
spring.datasource.password=root
```

SpringBoot启动类添加注解

```java
@SpringBootApplication
// 下面注解的含义是令Mybatis框架扫描指定包中的所有接口和类
// 相当于为指定包中所有类和接口都添加了@Mapper注解
@MapperScan("cn.tedu.knows.portal.mapper")
public class KnowsPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnowsPortalApplication.class, args);
    }
}
```

## MybatisPlus的使用一

MybatisPlus框架主要在Mybatis基础上新增了两大块功能

1. 自动生成基本的增删改查操作
2. 自动生成数据库表对应的代码

下面先来实现自动生成基本增删改查操作的功能

要想连接数据库,先按照数据库的表生成实体类

我们使用tag表为例进行操作演示

```java
@Data
public class Tag {
    private Integer id;
    private String name;
    private String createby;
    private String createtime;
}
```

下面创建mapper包,在mapper包中创建TagMapper接口

代码如下

```java
public interface TagMapper extends BaseMapper<Tag> {
    // TagMapper接口用于连接数据库操作tag表
    // TagMapper接口继承BaseMapper<Tag>,因为父接口中包含了基本的增删改查操作
    // BaseMapper<Tag>中<Tag>是表示要操作的表对应的实体类,不能写错
}
```

下面开始测试

```java
@Autowired(required = false)
TagMapper tagMapper;

@Test
public void getId(){
    //根据id查询标签对象
    Tag tag=tagMapper.selectById(1);
    System.out.println(tag);
}
```

每次编写@Autowired(required = false)其中(required = false)比较麻烦

我们可以在TagMapper接口上添加@Repository注解来代替每次都需要编写的(required = false)

测试类中

``` java
@Autowired
TagMapper tagMapper;
```

TagMapper接口中

```java
@Repository
public interface TagMapper extends BaseMapper<Tag> {
}
```

今后每次使用@Autowired获得TagMapper对象时直接编写即可

其他方法测试:

```java
@Test
public void addTag(){
    Tag tag=new Tag();
    tag.setId(21);
    tag.setName("微服务");
    tag.setCreateby("admin");
    tag.setCreatetime("2021-12-27 11:51:50");
    // 所有增删改操作都会返回一个int类型的返回值
    // 返回的int值表示本次数据库操作对数据库影响的行数
    int num=tagMapper.insert(tag);
    if(num==1){
        System.out.println("新增成功");
    }else{
        System.out.println("新增失败");
    }
}

@Test
public void getAll(){
    // 全查tag表中所有对象
    // selectList是查询返回List的方法
    // 方法参数可以设置特定条件,参数设置为null表示无条件,既全查
    List<Tag> tags=tagMapper.selectList(null);
    for(Tag t: tags){
        System.out.println(t);
    }
}
@Test
public void updateTag(){
    //MybatisPlus支持按id修改单个对象的各个属性,但是不能修改id
    // 使用方法一般是先将要修改的对象从数据库中查询出来
    // 将要修改的属性进行修改(只能修改非id列)
    // 然后执行方法修改数据库中对应的列
    Tag tag=tagMapper.selectById(21);
    // 可以修改除id外任何属性,不限属性个数
    tag.setName("分布式");
    tag.setCreateby("tom");
    // 执行修改
    int num=tagMapper.updateById(tag);
    System.out.println(num);
}

@Test
public void delete(){
    // MybatisPlus提供的按id删除行的方法
    // 注意不要删除1~20号标签,很多标签都在被引用
    int num=tagMapper.deleteById(21);
    System.out.println(num);
}
```

## 显示Mybatis运行的sql语句

上面章节中测试运行成功,但是并不能知道它底层运行的sql语句是什么样的

如果想要看到Mybatis运行的sql语句,实际上是非常简单的

因为Mybatis底层已经将运行的sql语句输出到了日志中

只是日志级别比较低,现在的Idea控制台没有显示而已

SpringBoot控制台输出的日志是有级别的,从不重要到重要分为了5个级别

* trace(痕迹):最不重要
* debug(调试):不重要:程序员测试项目过程中打桩输入用
* info(信息):一般,是程序运行的节点的输出:例如业务完成,运行成功等
* warn(警告):比较重要:一般输出可能威胁程序正常运行或影响程序正常运行的隐患信息
* error(错误):最重要的:严重的,程序运行过程中发生了异常,异常信息会以error级别输出到控制台

SpringBoot框架默认将info以及info以上级别的日志信息显示在控制台

而Mybatis将sql语句的运行信息输出到了debug级别,所以默认情况下看不到

我们可以在application.properties文件中修改SpringBoot默认的日志输出级别

代码如下

```properties
# 修改日志级别,这个操作也叫"修改日志输出门槛"
# cn.tedu.knows.portal包中的所有类,debug及以上的级别的信息会显示在控制台
logging.level.cn.tedu.knows.portal=debug
```

## MybatisPlus的使用二

MybatisPlus第二大块功能就是"代码生成器"

我们会为数据库中的每一张表生成它对应的一套接口类和实体类等java文件

以user表为例:

User(实体类)

UserController(控制器)

UserMapper(持久层)   

IUserService UserServiceImpl(业务逻辑层)

就需要5个类,其他共14张表,类型过多

浪费时间,工作量大,内容重复,没有技术含量

MybatisPlus给我们提供了直接生成它们的解决方案

因为这个工作是一次性的,所以建议新建一个子项目做这个工作

我们需要在resources文件夹下创建ftl的文件夹

在ftl文件夹在创建一个mapper.java.ftl的文件

打开CodeGenerator类,找到并运行类中的main方法

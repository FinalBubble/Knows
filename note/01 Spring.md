# Spring

## SpringMVC的功能

M(Model)模型：Java实体类

V(View)视图：页面

C(Controller)控制器：Controller类

SpringMVC的功能主要是实现V和C之间的数据传递，能够大大简化数据传递难度

## SpringBoot的功能

boot：启动

SpringBoot框架提供一个标准的项目格式

- SpringBoot框架自带maven
- SpringBoot框架通过配置可以自带web服务器(tomcat)和项目结构(如：static目录)
- SpringBoot通过添加相关依赖，可以自动对很多框架进行初始化配置，简化程序员的配置公章

## Spring框架

### 什么是Spring

Spring框架是SpringMVC和SpringBoot等框架的根框架，其他由Spring开头命名的框架都是由Spring框架衍生而来。但是Spring框架本身的功能并不复杂，核心功能只有两个：IoC\DI和AOP

### IoC的实现

IoC(Inversion of Contol)：控制反转，不同于之前所写的“主动控制”思想的程序，即程序中需要什么就实例化什么，实际情况就是实例化简单对象比较合适，但是需要获得复杂对象时，代码会比较臃肿。控制反转则是在编写程序之前，事先将需要的所有对象保存在在“外部容器”，“外部容器”可以进行对象的关联操作，在需要时直接从“外部容器”中获取，省去实例化各个对象并拼装的过程



## 组件扫描实现IoC

我们之前使用了@Bean注解保存对象到Spring容器，实际上"组件扫描"方式也可以将对象保存到Spring容器

- 在一个类上添加@Component注解，表示当前类会自动实例化对象保存到Spring容器中
- 组件扫描也需要编写配置类
  - @ComponentScan注解,指定组件扫描的包，指定的包中的所有类和子包中的所有类都会被扫描， 如果被扫描的类上带有@Component注解,那么这个类就会被实例化并保存到Spring容器，由扫描保存到Spring容器中的对象,id为这个类名首字母小写的名称 Cat->cat

经过测试输出@Component注解打印的对象所有属性都是null

这也是@Bean方式保存到Spring和组件扫描方式保存到Spring的主要区别

如果希望组件扫描的对象能够有初始值,只能在类中为其赋值

* 在属性声明的位置直接赋值
* 编写一个构造方法,在其中为属性赋值

### Spring IoC注意事项

上面的内容中有一些细节是需要大家注意的

### 关于组件扫描的注解

我们在完成组件扫描保存到Spring容器时,使用的注解是@Component

实际上除了这个注解还有其他注解也有相同的功能

* @Controller:控制器
* @Service:业务逻辑
* @Repository:数据仓库
* @Component:通用组件

他们的区别仅仅是语义的区分

就是让程序员在观察分析代码时,直接知道当前类的功能

### 组件扫描保存对象的名称

@Bean注解保存对象的名称是依据方法名

组件扫描的方式依据的是当前的类名,而且要将当前类名的首字母小写

我们也可以自定义组件扫描保存对象的名称

```java
@Component("hehe")
```

### 组件扫描生成对象id的特殊情况

一般情况下，组件扫描的方式依据的是当前的类名,而且要将当前类名的首字母小写

- 例如：Cat->cat，Dog->dog


但是如果类名是连续的两个或两个以上的大写字母开头命名的话,就不会将首字母小写了,而是使用原类名保存到Spring容器中

- 例如：VIPStu -> VIPStu  不变!


## Spring Bean 管理

Bean其实就是Spring容器中保存的java对象

Bean的管理就是对Spring容器中保存的java对象的形态进行进一步管理和配置,以达到符合各种使用场景的目的

### Bean的作用域配置

什么是作用域

作用域英文Scope

所谓作用域的区分就是

程序中使用的对象有些是从始至终只需要一个对象,有些是需要反复实例化的

Spring给我们提供了两种可选的作用域

* singleton(单例):Spring容器中该类型的对象从始至终只会出现一个对象,无论获取多少次都是同一个对象
* prototype(原型):Spring容器中该类型的对象每获取一次,就会实例化一个新的对象返回,每次获得的都是新对象

#### 单例作用域

Spring框架默认情况下,保存到Spring容器中的对象就是单例的，Spring容器中默认情况下,多次获得同一id对象时,返回的对象都是同一引用的，这就是"单例"作用域的特征

优点:

节省内存,方便修改当前Spring容器中一个对象的全部属性

缺点:

因为所有引用指向同一对象,不能实现某一个对象需要的个性化属性的赋值或修改

![image-20211223162054049](F:\BaiduNetdisk_Downloads\spring-test2109-master\note\day01\111.png)

#### 原型作用域

先学习怎么讲Spring容器中的对象设置为原型作用域

@Bean方式设置:

```java
@Bean
@Scope("prototype")
```

组件扫描方式

```java
@Component
@Scope("prototype")
```

设置完原型作用域之后,进行测试，结果证明原型作用域下,t1和t2分别引用了不同的对象

原型作用域使用

- 优点:每次从Spring容器中获得新对象,对象之间互不影响,都可以进行个性的赋值或操作
- 缺点:每个对象都是单独占用堆中空间的,如果反复获得占用内存较多

![image-20211223162314007](F:\BaiduNetdisk_Downloads\spring-test2109-master\note\day01\222.png)

### Bean的惰性初始化

Spring中另外一个比较重要的Bean管理就是惰性初始化

惰性初始化也有一个别名叫"懒加载"

#### 什么是惰性初始化

指Spring容器中保存的单例作用域对象

可以设置为惰性初始化,实现能够在程序运行到需要当前对象时,再实例化

避免容器实例化时初始化但是程序运行过程中使用不到的情况

#### 为什么需要惰性初始化

程序的一次运行可能并不会将Spring容器中的所有组件都使用到

如果有一个比较消耗内存的对象保存到了Spring容器,但是运行时没有使用,那么这个对象使用的内存就浪费了

我们这时将该对象设置为惰性初始化,让它不在Spring容器实例化时实例化,而修改为在需要它时再实例化它,这样就能针对性的使用内存,不会浪费内存白白实例化它了

惰性初始化也有缺点,如果每个组件都使用惰性初始化,那么运行时需要呢个对象再实例化哪个对象就会严重的拖慢程序运行速度,所以惰性初始化不要大范围使用

而Spring容器中默认保存的对象也不是惰性初始化的

### 测试默认情况下对象实例化的时机

我们以编写的Cat类为例

在Cat类中编写一个无参构造方法代码如下

```java
public Cat(){
    // 无参构造方法输出字符串
    System.out.println("Cat类实例化了!");
}
```

下面修改一下测试类,代码如下

```java
public class CatTest {

    AnnotationConfigApplicationContext acac;
    @Before
    public void init(){
        acac=new AnnotationConfigApplicationContext(CatConfig.class);
        System.out.println("init方法运行完毕");
    }
    @After
    public void destroy(){
        acac.close();
    }
    @Test
    public void cat(){
        Cat cat=acac.getBean("cat",Cat.class);
        System.out.println(cat);
    }
}
```

运行结果为

```
Cat类实例化了!
init方法运行完毕
Cat{name='小苍', color='三花', age=4}
```

### 设置惰性初始化效果

设置惰性初始化的方法

组件扫描:

```java
@Component
@Lazy
```

@Bean方式

```java
@Bean
@Lazy
```

原型作用域的对象不会出现需要惰性初始化的需求

因为原型作用域本身就是什么时候需要什么时候实例化对象



## DI 依赖注入

### 什么是依赖注入

依赖注入DI(Dependency Injection)

要想搞清楚依赖注入,先需要明确什么是依赖：

- 生活中对象之间的依赖关系:人类的写字方法需要依赖笔类型的对象,所以我们说人类依赖笔类
- 程序中依赖关系的实现:A类中的方法需要B类对象才能执行,我们说A类依赖B类

所谓注入就是将一个复杂对象的各个组件装配的过程，最终能够实现直接从Spring容器中获得拼装好的复杂对象的功能

### 依赖程序的实现

依赖注入的目标就是从容器中直接获得拼装好的对象，而我们学习的DI依赖注入就是完成这个工作的



### 依赖注入的实现

@Autowired就是自动装配的意思

效果是Spring会自动从Spring容器中搜索合适的对象并为当前属性赋值

### @Bean方式实现依赖注入

组件扫描方式可以实现依赖注入

### 依赖注入失败情况

顺利完成依赖注入是因为Spring容器中恰巧包含需要依赖的对象

**如果Spring容器中没有相应的对象**

测试类运行就会发生错误

错误信息中包含:

"expected at least 1 bean"

表示Spring容器中没有需要类型的对象

我们需要先将这个类型的对象保存到Spring容器中

### 组件扫描实现依赖注入的补充

我们之前也实现了组件扫描环境下使用@Autowired实现依赖注入

但是它也会遇到上面章节的问题

例如

```java
@Autowired
private SnakeLance snakeLance;
```

上面代码的意思是从Spring容器中寻找SnakeLance类型的对象

如果Spring容器中有两个以及两个以上对象时

它会优先选择id为snakeLance的对象

但是如果Spring容器中没有snakeLance名字的对象,就会报错

原因和上面章节中的一样

在这个情况下如果要选择其中的某一个对象作为依赖,就需要使用@Qualifier注解来指定要选中的id名称

```java
// 选择id为lance1的对象注入到snakeLance属性中
@Autowired
@Qualifier("lance1")
private SnakeLance snakeLance;
```

## IoC\DI解耦

### 什么是耦合

解耦:解开耦合

耦合的概念:

- 指程序中的各组件(对象)互相依赖的紧密程度
-  如果依赖关系容易更换,那么他们的依赖程度就是不紧密的
- 如果依赖关系不容易更换,那么他们的依赖程度就是紧密的

关系紧密,耦合就紧,需要修改代码时,影响代码就多；关系不紧密,耦合就松,需要你修改代码时,影响代码就少

在程序中,我们希望修改代码时,尽量少的修改代码就能完成效果,能更容易的扩展与维护我们的功能,所以我们追求的程序是松耦合的

程序中要想实现解耦,那么就需要将类依赖的类型修改为一个抽象的类型(接口和抽象类)

#  Junit测试

正常学习编写代码时，采用main方法对编写好的代码进行测试操作，但是使用main方法进行测试操作会有很多代码冗余。

可以使用Junit进行测试操作，Junit翻译为java单元测试，能够在一个类中编写多个测试方法，并且还可以将多个方法需要编写的重复代码通过配置来简化。

# Spring Boot 集合聚合项目

## 什么是聚合项目

Maven提供的一种项目结构，简单来说就是我们创建的项目是一个父级项目包含了多个子项目的结构，每个子项目编写自己的代码,和其他子项目不干扰，方便项目的模块化开发和维护，这就是聚合项目

## 创建聚合项目

以商城为例

一个商城项目要分为很多模块,最典型的就是前台和后台

一般来说一定是两个项目

前台项目对接用户请求

后台项目支出商家管理员对商品和用户的维护等

我们可以通过聚合项目实现一个父项目中包含两个子项目的结构

创建一个父项目

创建完父项目修改它的pom文件内容

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.6.2</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>cn.tedu</groupId>
    <artifactId>jd</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>jd</name>
    <description>Demo project for Spring Boot</description>
    <properties>
        <java.version>1.8</java.version>
    </properties>
    <!-- 声明当前项目是没有具体代码的
    子项目继承父项目相当于继承了pom文件
    -->
    <packaging>pom</packaging>
    

</project>
```

然后开始创建子项目

两个项目的父子关系需要双方确认

父项目认儿子:父项目pom文件修改

```xml
<!-- 声明当前项目是没有具体代码的
    子项目继承父项目相当于继承了pom文件
    -->
    <packaging>pom</packaging>
    <!-- 
    modules标签中声明所有当前项目的子项目名称
    也就是父认子的配置
    -->
    <modules>
        <module>jd-shop</module>
<!--        <module></module>-->
    </modules>
```

子项目继承父项目

```xml
<parent>
    <groupId>cn.tedu</groupId>
    <artifactId>jd</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
```



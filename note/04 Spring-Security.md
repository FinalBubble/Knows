# Spring 安全框架

## 什么是Spring安全框架

Spring安全:Spring-Security

是Spring框架提供的一个安全管理框架

它能够提供一个安全可靠的登录逻辑,实现登录功能,我们只需要按照它的要求提供一些信息和一些配置就可以编写出企业级安全的登录功能更

使新手程序员可能编写出可靠的登录代码

除了登录之外,它还附带了权限管理功能,能够判断某些用户是否可以运行某些功能

例如:学生是不能回答问题的,但是讲师可以

## 启动Spring-Security

启动Spring-Security非常简单

添加下面依赖到pom.xml文件即可

```xml
<!-- Spring Security -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

一旦添加这个依赖，当前项目整体就在Spring-Security框架的保护下了

如果访问当前项目的任何资源都要求先登录才能访问，默认登录方式，用户名:user，密码:项目启动时控制台会随机出现登录密码

<img src="F:\BaiduNetdisk_Downloads\knows2109-master\note\day01\image-20211126165326068.png" alt="image-20211126165326068" style="zoom:50%;" />

无论是页面还是控制器方法,都是必须登录后才能访问，一旦登录成功所有资源都对当前用户开放

## 自定义用户名和密码

我们可以在application.properties配置中添加自定义的登录用户名和密码

一旦设置自定义用户名和密码,Spring-Security就不会生成随机密码了,user用户作废

```properties
# Spring-Security自定义用户名和密码配置
spring.security.user.name=admin
spring.security.user.password=123456
```

上面的配置暴露了一个问题，任何人看到配置文件都可以随意登录网站，怎么能实现即使看到配置文件也不知道我的密码呢?使用密码加密技术

## 密码加密

加密算法就是将原数据经过一系列加密计算得到另一个数据的过程，我们常见的加密规则:bcrypt,md5,sha

![image-20211126172436525](F:\BaiduNetdisk_Downloads\knows2109-master\note\day01\image-20211126172436525.png)

## 密码加密的实现

```java
// 密码加密操作
PasswordEncoder encoder=new BCryptPasswordEncoder();
// 加密操作
@Test
public void pwd(){
    String str="123456";
    // 执行加密操作encode方法参数是要加密的字符串,返回值是加密结果
    String pwd=encoder.encode(str);
    System.out.println(pwd);
    //$2a$10$7sArRpk0xhLtR7.c.T9Uw.XYkqIpQFhPBGsYJmY/Qk1EgkbDUtl4m
}
```

运行得到加密加过，我们发现每次加密结果是不同的，因为如果每次加密结果是固定了,反而容易被人破解,并不安全，bcrypt算法采用了"随机加盐"技术,每次加密结果不同,提高了安全等级，我们获得了加密的字符串,下面就要进行反向的验证，验证给定的字符串能不能加密为指定的加密结果,如果能即为验证通过

```java
// 验证操作
@Test
public void match(){
    // 验证需要提供两个参数:1.原字符串  2.加密字符串
    // 方法能验证原字符串是否可以加密为加密字符串
    // 返回结果为boolean类型
    boolean b=encoder.matches("123456",
      "$2a$10$7sArRpk0xhLtR7.c.T9Uw.XYkqIpQFhPBGsYJmY/Qk1EgkbDUtl4m");
    System.out.println("验证结果为:"+b);
}
```

上面通过验证证明我们生成的字符串能够验证通过,随便修改任何字符验证都会失败，下面实现配置文件中保存加密密码的效果

```properties
# application.properties

# Spring-Security自定义用户名和密码
spring.security.user.name=admin
spring.security.user.password={bcrypt}$2a$10$7sArRpk0xhLtR7.c.T9Uw.XYkqIpQFhPBGsYJmY/Qk1EgkbDUtl4m
```

{bcrypt}是"算法id"的声明定义，表示后面的字符串是由bcrypt算法加密得来的，登录时会按照相同的算法来验证

## Spring-Security配置类登录与权限管理

上面是将登录用户信息保存在application配置文件中，实际登录时一定是登录数据库中的用户,所以登录一定写在java代码中，我们先不连接数据库,单纯的在java代码中进行一个登录的配置，顺便开启权限管理功能



## 用户与权限

上面章节中我们学习了java代码中配置要登录的用户信息

我们知道了要实现登录需要提供的信息为:

1.用户名

2.密码(加密的)

3.当前用户的所有资格(授权)

上面的信息都在数据库中保存,用户名和密码直接从user表中获得即可

但是用户的授权(权限)是需要一个相对复杂的查询才能得到的

![image-20211129102102134](F:/BaiduNetdisk_Downloads/knows2109-master/note/day02/image-20211129102102134.png)

上面的5张表是表示的用户\角色\权限的关系

我们需要通过用户的id查询出这个用户具有什么权限,然后保存到Spring-Security里

因为他们都是多对多的关系所有要连接他们的中间表user_role和role_permission

我们需要编写的sql语句如下

```sql
SELECT p.id , p.name
FROM user u
LEFT JOIN user_role ur ON u.id=ur.user_id
LEFT JOIN role r       ON r.id=ur.role_id
LEFT JOIN role_permission rp
                       ON r.id=rp.role_id
LEFT JOIN permission p ON p.id=rp.permission_id
WHERE u.id=3
```



## 实现Spring-Security数据库用户登录

Spring-Security框架规定好了我们如何编码来进行登录操作，我们只需要按它的要求完成操作步骤即可,其他所有登录细节都由Spring-Security内部封装了，我们需要完成Spring-Security框架提供的一个接口UserDetailsService的实现类编写,实现类中实现它要求的方法，这个实现方法的参数就是用户在登录表单中输入的用户名(Spring-Security框架会为它赋值),返回值是一个UserDetails类型的对象,这个对象中包含用户名密码权限等信息

## 设置页面权限

一个网站不应该所有页面都需要登录之后才能访问



```java
// 设置页面权限的配置方法
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable() // 设置禁用防跨域攻击
        .authorizeRequests() // 开始设置页面的访问权限
        //指定路径
        .antMatchers(
                "/index_student.html",
                "/css/*",
                "/js/*",
                "/img/**",
                "/bower_components/**"
        ).permitAll() // 上面设置的路径全部放行(不需登录就能访问)
        .anyRequest() // 其他请求
        .authenticated()  // 需要登录才能访问
        .and()        // 是一个分割,表示上面的配置完毕,开始下面新的配置
        .formLogin();  //使用表单登录
}
```

## 自定义登录页

Spring-Security框架提供的登录页功能过于简单了,没有中文的支持

我们希望能够使用我们自己编写的登录页来替换掉它

替换的操作也是配置文件中进行

就在刚刚设置放行的方法中添加配置即可

```java
// 设置页面权限的配置方法
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable() // 设置禁用防跨域攻击
        .authorizeRequests() // 开始设置页面的访问权限
        //指定路径
        .antMatchers(
                "/index_student.html",
                "/css/*",
                "/js/*",
                "/img/**",
                "/bower_components/**",
                "/login.html"
        ).permitAll() // 上面设置的路径全部放行(不需登录就能访问)
        .anyRequest() // 其他请求
        .authenticated()  // 需要登录才能访问
        .and()        // 是一个分割,表示上面的配置完毕,开始下面新的配置
        .formLogin()  //使用表单登录
        .loginPage("/login.html")  // 配置登录页为login.html
        .loginProcessingUrl("/login") // 配置登录表单提交信息的路径
        .failureUrl("/login.html?error")  //配置登录失败时的页面
        .defaultSuccessUrl("/index_student.html")// 登录成功后的页面*
        .and()
        .logout()   //开始设置登出
        .logoutUrl("/logout")  // 设置登出路径
        .logoutSuccessUrl("/login.html?logout"); // 设置登出成功后的页面
    
    //* defaultSuccessUrl设置的是默认登录成功的页面路径
            //  特指用户没有特定指向页面时登录成功才会显示的页面
            //  如果用户因为指向了特定页面而进入的登录页面,在登录成功之后
            //  会显示用户指向的特定页面
}
```

重启服务尝试登陆

我们看到已经使用我们的登录页面替换掉了之前Spring-Security提供的登录页,并且能够登录成功
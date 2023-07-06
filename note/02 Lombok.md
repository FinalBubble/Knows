# Lombok简化开发

## 什么是Lombok

<img src="F:\BaiduNetdisk_Downloads\spring-test2109-master\note\day02\image-20211125172053331.png" alt="image-20211125172053331" style="zoom: 33%;" />

它的功能是帮助我们简化数据封装类的编写

简单来说就是之前使用快捷键生成的get\set方法和toString方法都不需要写了

![image-20211125172515133](F:\BaiduNetdisk_Downloads\spring-test2109-master\note\day02\image-20211125172515133.png)

可以看到,使用Lombok之后编写的代码显著减少了

## lombok常用注解

上面示例中我们只使用了@Data注解

实际上lombok还提供了其他注解,我们需要了解一下

* @Getter\@Setter:可以写在类或单个属性上,表示为当前类中所有属性或标记的单个属性生成get\set方法
* @ToString:编写在类上,会自动重写toString方法,方法会输出包含所有属性值的信息
* @EqualsAndHashCode:编写在类上,重写Object类的equals和hashcode方法
* @Data:@Getter\@Setter\@ToString\@EqualsAndHashCode注解的和
* @NoArgsConstructor:编写在类上,会自动生成这个类的无参构造
* @AllArgsConstructor:编写在类上,会自动生成这个类的全部参数的构造方法
* @Slf4j:编写在类上,会自动在当前类中添加一个记录日志的属性log,方便日志的编写

## Lombok的争议

因为Lombok生成的代码不是们写的

lombok内部生成代码的细节可能和我们预想的不同或违反公司的代码规范,造成程序的bug或隐患,所以有些公司在开发中不允许使用Lombok
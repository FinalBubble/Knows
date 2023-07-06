package org.fianlbubble.cat;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
// @ComponentScan注解,指定组件扫描的包
// 指定的包中的所有类和子包中的所有类都会被扫描
// 如果被扫描的类上带有@Component注解,那么这个类就会被实例化并保存到Spring容器
// 由扫描保存到Spring容器中的对象,id为这个类名首字母小写的名称 Cat->cat
@ComponentScan("org.fianlbubble.cat")
@Scope("prototype")
public class CatConfig {

}
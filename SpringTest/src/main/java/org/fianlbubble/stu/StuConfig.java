package org.fianlbubble.stu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Configuration注解的含义是表示当前类是配置Spring框架信息的
// 加了这个注解的类可以向Spring容器保存信息
@Configuration
public class StuConfig {
    //@Bean注解下面要编写一个方法
    //这个方法啊的返回值会保存到Spring容器中
    //保存的对象由对象的id或称为name来唯一标识
    //下面方法的方法名就是这个对象的name/id
    @Bean
    public Stu stu(){
        //实例化一个Stu对象并返回已保存到Spring容器
        Stu s = new Stu();
        s.setId(1);
        s.setName("孙悟空");
        s.setGender("男");
        return s;
    }
}

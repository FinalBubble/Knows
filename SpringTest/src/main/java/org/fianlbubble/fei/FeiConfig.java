package org.fianlbubble.fei;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.fianlbubble.fei")
public class FeiConfig {
    @Bean
    public SnakeLance lance1(){
        return new SnakeLance();
    }

    @Bean
    public SnakeLance lance2(){
        return new SnakeLance();
    }

    // @Bean标记的方法的参数列表中如果声明了参数
    // Spring会自动从Spring容器中寻找合适对象,为这个参数赋值
    // 也就是说上面保存到Spring容器中的lance对象,就会自动赋值给参数sl
    // 方法中又将sl赋值给了zf的属性,所以实现了依赖注入
    //@Bean
    //public ZhangFei fei(SnakeLance lance1){
    //    ZhangFei zf=new ZhangFei();
    //    // 将丈八蛇矛类型的参数赋值给zf对象依赖的属性
    //    zf.setSnakeLance(lance1);
    //    return zf;
    //}
}

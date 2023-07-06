package org.fianlbubble.tea;

import org.fianlbubble.Tea.Tea;
import org.fianlbubble.Tea.TeaConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class TeaTest {

    AnnotationConfigApplicationContext annotationConfigApplicationContext;

    @Before
    public void init(){
        annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(TeaConfig.class);
    }
    @After
    public void destory(){
        annotationConfigApplicationContext.close();
    }
    @Test
    public void tea(){
        Tea tea = annotationConfigApplicationContext.getBean("tea", Tea.class);
        System.out.println(tea);
    }

    // 原型作用域测试
    @Test
    public void prototype(){
        // 从Spring容器中获得tea,每次获得都是一个新的对象
        Tea t1=annotationConfigApplicationContext.getBean("tea",Tea.class);
        Tea t2=annotationConfigApplicationContext.getBean("tea",Tea.class);

        t1.setName("范范");
        System.out.println(t1);
        System.out.println(t2);

        System.out.println(t1==t2);
    }
}

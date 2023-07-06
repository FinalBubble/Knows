package org.fianlbubble.cat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CatTest {

    AnnotationConfigApplicationContext annotationConfigApplicationContext;
    @Before
    public void init(){
        annotationConfigApplicationContext=new AnnotationConfigApplicationContext(CatConfig.class);
        System.out.println("init方法运行完毕");
    }
    @After
    public void destroy(){
        annotationConfigApplicationContext.close();
    }
    @Test
    public void cat(){
        Cat cat=annotationConfigApplicationContext.getBean("cat",Cat.class);
        System.out.println(cat);
    }
}

package org.fianlbubble.guan;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class GuanTest {
    AnnotationConfigApplicationContext annotationConfigApplicationContext;
    @Before
    public void init(){
        annotationConfigApplicationContext = new AnnotationConfigApplicationContext(GuanConfig.class);
    }

    @After
    public void destory(){
        annotationConfigApplicationContext.close();
    }
    @Test
    public void guan(){
        DragonBlade blade=annotationConfigApplicationContext.getBean("dragonBlade",DragonBlade.class);
        GuanYu guanYu=annotationConfigApplicationContext.getBean("guanYu",GuanYu.class);
        //guanYu.setDragonBlade(blade);
        guanYu.fight();
    }
}

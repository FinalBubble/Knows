package org.fianlbubble.ou;

import org.fianlbubble.guan.GuanConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OuTest {
    AnnotationConfigApplicationContext annotationConfigApplicationContext;
    @Before
    public void init(){
        annotationConfigApplicationContext = new AnnotationConfigApplicationContext(OuConfig.class);
    }

    @After
    public void destory(){
        annotationConfigApplicationContext.close();
    }
    @Test
    public void ou(){
        GuanYu guanYu=annotationConfigApplicationContext.getBean("guanYu",GuanYu.class);
        guanYu.fight();
    }
}

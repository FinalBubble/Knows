package org.fianlbubble.fei;

import org.fianlbubble.guan.DragonBlade;
import org.fianlbubble.guan.GuanConfig;
import org.fianlbubble.guan.GuanYu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FeiTest {
    AnnotationConfigApplicationContext annotationConfigApplicationContext;
    @Before
    public void init(){
        annotationConfigApplicationContext = new AnnotationConfigApplicationContext(FeiConfig.class);
    }

    @After
    public void destory(){
        annotationConfigApplicationContext.close();
    }
    @Test
    public void fei(){
        ZhangFei fei = annotationConfigApplicationContext.getBean("zhangFei", ZhangFei.class);
        fei.fight();
    }
}

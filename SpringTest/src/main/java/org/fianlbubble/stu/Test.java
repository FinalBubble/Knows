package org.fianlbubble.stu;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        //初始化Spring容器对象
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(StuConfig.class);
        Stu stu = annotationConfigApplicationContext.getBean("stu", Stu.class);
        System.out.println(stu);
        annotationConfigApplicationContext.close();
    }
}

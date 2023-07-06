package org.fianlbubble.stu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StuTest {

    //JUnit支持在运行指定方法之前或之后运行额外代码
    //来减少编码的重复和冗余，提高开发效率

    //声明Spring容器对象为属性，以便多个方法调用
    AnnotationConfigApplicationContext annotationConfigApplicationContext;
    //@Before注解标记的方法会在@Test测试方法运行之前运行
    @Before
    public void init(){
        //在运行@Test标记的测试方法之前，先将annotationConfigApplicationContext实例化
        annotationConfigApplicationContext = new AnnotationConfigApplicationContext(StuConfig.class);
    }

    //在@Test测试方法运行之后运行下面的代码
    @After
    public void destory(){
        annotationConfigApplicationContext.close();
    }

    //编写@Test注解，表示下面的方法是一个测试方法
    //而测试方法是可以直接运行的
    //方法名称随意，没有规定
    @Test
    public void stu(){
        //初始化Spring容器对象
        //AnnotationConfigApplicationContext annotationConfigApplicationContext =
        //        new AnnotationConfigApplicationContext(StuConfig.class);
        Stu stu = annotationConfigApplicationContext.getBean("stu", Stu.class);
        System.out.println(stu);
        //annotationConfigApplicationContext.close();
    }


    // 测试单例作用域效果
    @Test
    public void singleton(){
        // 从Spring容器中先后获得两次stu对象
        Stu s1=annotationConfigApplicationContext.getBean("stu",Stu.class);
        Stu s2=annotationConfigApplicationContext.getBean("stu",Stu.class);

        // 修改s1的属性
        s1.setName("泼猴");
        System.out.println(s2);

        System.out.println(s1==s2);

    }

}

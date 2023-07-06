package org.fianlbubble.cat;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

// 一个类上添加@Component注解
// 表示当前类会自动实例化对象保存到Spring容器中
@Component
@Lazy
public class Cat {

    private String name;
    private String color;
    private Integer age;

    public Cat() {
        //无参构造方法输出字符串
        System.out.println("Cat类实例化了!");
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    // 略
}

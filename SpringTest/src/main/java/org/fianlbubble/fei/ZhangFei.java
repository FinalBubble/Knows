package org.fianlbubble.fei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ZhangFei {
    private String name="张翼德";
    @Autowired
    @Qualifier("lance1")
    private SnakeLance snakeLance;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SnakeLance getSnakeLance() {
        return snakeLance;
    }

    public void setSnakeLance(SnakeLance snakeLance) {
        this.snakeLance = snakeLance;
    }

    public void fight(){
        System.out.println(name+"使用"+snakeLance+"战斗");
    }
}

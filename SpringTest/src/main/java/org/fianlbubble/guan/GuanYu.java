package org.fianlbubble.guan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GuanYu {
    private String name="关羽";
    // 呈现关羽与青龙偃月刀的依赖关系的代码
    @Autowired
    private DragonBlade dragonBlade;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DragonBlade getDragonBlade() {
        return dragonBlade;
    }

    public void setDragonBlade(DragonBlade dragonBlade) {
        this.dragonBlade = dragonBlade;
    }
    // 关羽的战斗方法
    public void fight(){
        System.out.println(name+"使用"+dragonBlade+"战斗");
    }
}

package org.fianlbubble.ou;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GuanYu {
    private String name="关云长";
    // 依赖关系由之前的具体类型修改为抽象类型,实现解耦
    // 运行时会自动从Spring容器中寻找Weapon类型对象来注入依赖
    // 更换依赖对象时,只需要更换保存到Spring容器中的对象即可
    @Autowired
    @Qualifier("dragonBlade")
    private Weapon weapon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void fight(){
        System.out.println(name+"使用"+weapon+"战斗");
    }
}

package org.fianlbubble.ou;

import org.springframework.stereotype.Component;

@Component
public class DragonBlade implements Weapon{
    private String name="青龙偃月刀";

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String toString() {
        return "DragonBlade{" +
                "name='" + name + '\'' +
                '}';
    }
}

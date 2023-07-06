package org.fianlbubble.guan;

import org.springframework.stereotype.Component;

@Component
public class DragonBlade {
    private String name="青龙偃月刀";

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }

}

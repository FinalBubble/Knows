package org.fianlbubble.ou;

import org.springframework.stereotype.Component;

@Component
public class SnakeLance implements Weapon{
    private String name="丈八蛇矛";

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String toString() {
        return "SnakeLance{" +
                "name='" + name + '\'' +
                '}';
    }
}

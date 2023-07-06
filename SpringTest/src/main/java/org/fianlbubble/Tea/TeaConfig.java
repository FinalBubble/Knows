package org.fianlbubble.Tea;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TeaConfig {

    @Bean
    @Scope("prototype")
    public Tea tea(){
        Tea tea = new Tea();
        tea.setId(1);
        tea.setName("唐僧");
        tea.setGender("男");
        return tea;
    }
}

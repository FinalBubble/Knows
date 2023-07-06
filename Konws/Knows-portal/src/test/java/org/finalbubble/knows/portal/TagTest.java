package org.finalbubble.knows.portal;

import org.finalbubble.knows.portal.mapper.TagMapper;
import org.finalbubble.knows.portal.model.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

// SpringBoot环境下要想测试必须在测试类上添加这个注解

@RunWith(SpringRunner.class)
@SpringBootTest
public class TagTest {
    @Autowired
    TagMapper tagMapper;
    // 测试TagMapper是否可用

    @Test
    public void getAll(){
        List<Tag> tags=tagMapper.selectList(null);
        for(Tag t:tags){
            System.out.println(t);
        }

    }
}

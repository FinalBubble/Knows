package org.finalbubble.knows.portal;

import org.finalbubble.knows.portal.mapper.TagMapper;
import org.finalbubble.knows.portal.mapper.UserMapper;
import org.finalbubble.knows.portal.model.Permission;
import org.finalbubble.knows.portal.model.Tag;
import org.finalbubble.knows.portal.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    //密码加密操作
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //加密操作
    @Test
    public void pwd(){
        String str = "123456";
        // 执行加密操作encode方法参数是要加密的字符串,返回值是加密结果
        String pwd = passwordEncoder.encode(str);
        System.out.println(pwd);
        //$2a$10$0eA0gH4eLIjKU8TjRQzkneL4W1S0yZQqYoLz2vXFcJJNJd3SN6TZq
        //$2a$10$lsdk8zTW83/fCS5LyJGjJO1r2qG1uKI5giRVdrMgnT6kRWEU3r1kK
    }

    //验证操作
    @Test
    public void match(){
        // 验证需要提供两个参数:1.原字符串  2.加密字符串
        // 方法能验证原字符串是否可以加密为加密字符串
        // 返回结果为boolean类型
        boolean b = passwordEncoder.matches("123456","$2a$10$0eA0gH4eLIjKU8TjRQzkneL4W1S0yZQqYoLz2vXFcJJNJd3SN6TZq");
        boolean c = passwordEncoder.matches("123456","$2a$10$lsdk8zTW83/fCS5LyJGjJO1r2qG1uKI5giRVdrMgnT6kRWEU3r1kK");
        System.out.println(b);
        System.out.println(c);
    }

    @Autowired
    UserMapper userMapper;
    @Test
    public void userTest(){
        // 根据用户名获得用户对象信息
        //  id:11用户名 st2    id:3用户名 tc2
        User user=userMapper.findUserByUsername("tc2");
        // 根据用户id查询用户权限
        List<Permission> list=
                userMapper.findUserPermissionsById(user.getId());
        System.out.println(user);
        for(Permission p: list){
            System.out.println(p);
        }
    }
}

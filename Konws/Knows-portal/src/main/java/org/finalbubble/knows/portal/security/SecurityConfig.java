package org.finalbubble.knows.portal.security;

import org.finalbubble.knows.portal.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
// 启动Spring-Security配置的注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // 配置类要继承WebSecurityConfigurerAdapter
    // 这个父类提供了Spring-Security的标准配置方法
    // 我们在配置Spring-Security时直接重写它提供的方法即可
    // 下面的方法就是配置什么样的用户能够登录
    // 它能够代替application中配置的用户

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //String[] as={"answer","remove"};
        //auth.inMemoryAuthentication()
        //        .withUser("admin")
        //        .password("{bcrypt}$2a$10$lsdk8zTW83/fCS5LyJGjJO1r2qG1uKI5giRVdrMgnT6kRWEU3r1kK")
        //        .authorities(as);
        // 编写完上述配置
        // 我们就可以使用admin和123456登录了
        // tom用户拥有answer授权\资格
        // 这个授权可以访问需要这个授权才能执行的控制器方法
        auth.userDetailsService(userDetailsService);
    }

    // 设置页面权限的配置方法
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // 设置禁用防跨域攻击
                .authorizeRequests() // 开始设置页面的访问权限
                //指定路径
                .antMatchers(
                        "/index_student.html",
                        "/css/*",
                        "/js/*",
                        "/img/**",
                        "/bower_components/**",
                        "/login.html"
                ).permitAll() // 上面设置的路径全部放行(不需登录就能访问)
                .anyRequest() // 其他请求
                .authenticated()  // 需要登录才能访问
                .and()        // 是一个分割,表示上面的配置完毕,开始下面新的配置
                .formLogin()  //使用表单登录
                .loginPage("/login.html") // 配置登录页为login.html
                .loginProcessingUrl("/login")  // 配置登录表单提交信息的路径
                .failureUrl("/login.html?error")  //配置登录失败时的页面
                .defaultSuccessUrl("/index_student.html")// 登录成功后的页面*
                .and()
                .logout()   //开始设置登出
                .logoutUrl("/logout")  // 设置登出路径
                .logoutSuccessUrl("/login.html?logout"); // 设置登出成功后的页面

        //* defaultSuccessUrl设置的是默认登录成功的页面路径
        //  特指用户没有特定指向页面时登录成功才会显示的页面
        //  如果用户因为指向了特定页面而进入的登录页面,在登录成功之后
        //  会显示用户指向的特定页面
    }
}

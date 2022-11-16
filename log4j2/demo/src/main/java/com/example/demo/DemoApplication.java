package com.example.demo;

import com.hj.App;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @GetMapping("/test")
    public String test() {
        return "test";
    }


    @Bean
    public DefaultWebSecurityManager shiroFilter() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(new Realm() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public boolean supports(AuthenticationToken authenticationToken) {
                return false;
            }

            @Override
            public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
                return null;
            }
        });
        return defaultWebSecurityManager;
    }

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        // 创建ShiroFilterFactoryBean
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        filterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //配置受限资源,index是受限资源，authc
        Map<String, String> map = new HashMap<String, String>();
//        // /**代表匹配所有url
        map.put("/**", "authc");
//        // /user/login 是可以匿名访问的也就是公有资源
//        map.put("/user/login", "anon");
        filterFactoryBean.setFilterChainDefinitionMap(map);
        // 设置默认认证路径 其实shiro默认的认证路径就是login.jsp
//        filterFactoryBean.setLoginUrl("/login.jsp");
        filterFactoryBean.getFilters().put("authc", new Filter() {
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                System.out.println("authc");
                chain.doFilter(request, response);
            }
        });
        return filterFactoryBean;
    }
}

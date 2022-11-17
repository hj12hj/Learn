package com.hj.shiroconfig;

import org.apache.catalina.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: hj
 * @date: 2022/11/17
 * @time: 9:01 AM
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给ShiroFilter配置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //配置系统受限资源
        //配置系统公共资源
        Map<String, String> map = new HashMap<String, String>();
        map.put("/**", "authc");//表示这个资源需要认证和授权
        // 设置认证界面路径
//        shiroFilterFactoryBean.setLoginUrl("/login");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);


        return shiroFilterFactoryBean;
    }

    //创建安全管理器
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(CustomerRealm realm,DefaultWebSubjectFactory defaultWebSubjectFactory) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setSubjectFactory(defaultWebSubjectFactory);
        return securityManager;
    }

    @Bean
    public CustomerRealm customerRealm() {
        CustomerRealm realm = new CustomerRealm();
        return realm;
    }

    @Bean
    public DefaultWebSubjectFactory defaultWebSubjectFactory() {
        return new StatelessDefaultSubjectFactory();
    }

}

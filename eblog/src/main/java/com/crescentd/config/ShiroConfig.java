package com.crescentd.config;

import com.crescentd.shiro.AccountRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.net.httpserver.AuthFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dyh
 * @description:
 * @date 2020/4/28
 **/
@Slf4j
@Configuration
public class ShiroConfig {

    /**
    * @Description: 权限管理，配置主要是Realm的管理认证
    * @Param: accountRealm
    * @return: SecurityManager
    * @Author: dyh
    * @Date: 2020/4/28
    */
    @Bean
    public SecurityManager securityManager(AccountRealm accountRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(accountRealm);
        log.info("------------------>securityManager注入成功");
        return securityManager;
    }

    /**
    * @Description: Filter工厂，设置对应的过滤条件和跳转条件
    * @Param: securityManager
    * @return: ShiroFilterFactoryBean
    * @Author: dyh
    * @Date: 2020/4/28
    */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new HashMap<>();
        // 配置登录的url和登录成功的url
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/user/center");
        // 配置未授权跳转页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");

        map.put("/login","anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }


}
